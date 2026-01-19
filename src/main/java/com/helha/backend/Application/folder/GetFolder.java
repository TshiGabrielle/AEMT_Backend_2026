package com.helha.backend.Application.folder;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetFolder {
    private final IFolderRepository folderRepository;
    public GetFolder(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public Optional<DbFolder> execute(Long id){
        return folderRepository.findById(id);
    }
}
