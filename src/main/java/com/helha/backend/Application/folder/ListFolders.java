package com.helha.backend.Application.folder;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListFolders {
   private final IFolderRepository folderRepository;
   public ListFolders(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
   }
   public List<DbFolder> execute(Long userId) {
    if(userId != null){
        return folderRepository.findByUserId(userId);
    }
    return (List<DbFolder>) folderRepository.findAll();

   }
}
