package com.helha.backend.Application.folders.getAll;

import java.util.ArrayList;
import java.util.List;

public class GetFoldersTreeOutput {
    // liste de tous les dossiers
    public List<Folder> folders = new ArrayList<>();

    public static class Folder {
        public long id;
        public String name;

        // id du parent (null si dossier racineà
        public Long parentId;
    }
}