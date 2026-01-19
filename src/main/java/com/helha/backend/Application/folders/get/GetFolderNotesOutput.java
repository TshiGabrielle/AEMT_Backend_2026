package com.helha.backend.Application.folders.get;

import java.util.ArrayList;
import java.util.List;

public class GetFolderNotesOutput {

    // liste des notes du dossier
    public List<Note> notes = new ArrayList<>();

    // représentation simple d’une note pour le front
    public static class Note {
        public long id;
        public String name;
    }
}