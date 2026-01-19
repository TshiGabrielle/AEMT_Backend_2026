package com.helha.backend.Application.folder;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateFolder {
   private final IFolderRepository folderRepository;

   public UpdateFolder(IFolderRepository folderRepository) {
       this.folderRepository = folderRepository;
   }
   public Optional<DbFolder> execute(Long id,DbFolder folderUpdate) {
       return folderRepository.findById(id)
       .map(folder ->{
           folder.setName(folderUpdate.getName());
           return folderRepository.save(folder);
       });
   }
}
