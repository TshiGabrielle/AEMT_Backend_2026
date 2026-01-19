package com.helha.backend.Application.folder;

import com.helha.backend.Infrastructure.folder.IFolderRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteFolder {
    private final IFolderRepository folderRepository;

    public DeleteFolder(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public boolean execute(Long id) {
        if (folderRepository.existsById(id)) {
            folderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
