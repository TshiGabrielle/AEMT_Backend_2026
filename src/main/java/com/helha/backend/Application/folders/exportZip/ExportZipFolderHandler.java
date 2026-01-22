package com.helha.backend.Application.folders.exportZip;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ExportZipFolderHandler {
    private final IFolderRepository folderRepository;
    private final INoteRepository noteRepository;

    public ExportZipFolderHandler(
            IFolderRepository folderRepository,
            INoteRepository noteRepository
    ) {
        this.folderRepository = folderRepository;
        this.noteRepository = noteRepository;
    }

    public ExportZipFolderOutput handle(ExportZipFolderInput input) {
        // récupérer le dossier dans la db
        // si le dossier n'existe pas, on renvoie null
        DbFolder folder = folderRepository.findById(input.id()).orElse(null);
        if (folder == null) {
            return null;
        }

        try {
            // préparer un flux pour le zip (flux de mémoire où on va écrire le zip)
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // outil java qui permet d'écrire des fichiers dans un zip
            ZipOutputStream zip = new ZipOutputStream(baos);

            /*
            *  lancer la fct récursive qui va permettre d'ajouter les notes au dossier,
            *   parcourir les sous-dossiers et ajouter leur notes, etc
            * */
            exportFolderRecursive(folder, zip, folder.getName() + "/");

            // important de fermer le zip pour finaliser l'écriture
            zip.close();

            // on renvoie l'output contenant les bytes du zip et le nom du dossier (pour nommer le fichier téléchargé)
            return new ExportZipFolderOutput(
                    baos.toByteArray(),
                    folder.getName()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * fct récurisve qui exporte toutes les notes du dossier courant,
     * tous les sous-dossiers, toutes les notes des sous-dossiers, etc
     * */
    private void exportFolderRecursive(DbFolder folder, ZipOutputStream zip, String path) throws IOException {
        // récupérer les notes du dossier courant
        List<NoteRepository> notes = noteRepository.findByFolder_Id(folder.getId());

        // pour chaque note, on récupère le md et on l'ajoute au zip
        for (NoteRepository note : notes) {
            ZipEntry entry = new ZipEntry(path + note.getName() + ".md");

            zip.putNextEntry(entry);

            zip.write(note.getContent_markdown().getBytes());

            // fermer l'entrée
            zip.closeEntry();
        }

        // récupérer tous les sous-dossiers du dossier courant
        List<DbFolder> folders = folderRepository.findByParentId(folder.getId());

        // pour chaque sous-dossier, on appelle la fct
        for (DbFolder f : folders) {
            // on construit le chemin du sous-dossier dans le zip
            String childPath = path + f.getName() + "/";

            // appel récursif
            // traiter les sous-dossiers comme un dossier
            exportFolderRecursive(f, zip, childPath);
        }
    }
}
