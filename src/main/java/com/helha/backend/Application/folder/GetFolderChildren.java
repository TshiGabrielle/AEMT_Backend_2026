package com.helha.backend.Application.folder;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFolderChildren {
    private final IFolderRepository folderRepository;

    public GetFolderChildren(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public List<DbFolder> execute(Long parentId) {
        return folderRepository.findByParentId(parentId);
    }

}
