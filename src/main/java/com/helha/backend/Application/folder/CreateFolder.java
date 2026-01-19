package com.helha.backend.Application.folder;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateFolder {
    private final IFolderRepository folderRepository;

    public CreateFolder(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public DbFolder execute(DbFolder folder) {
        return folderRepository.save(folder);
    }
}
