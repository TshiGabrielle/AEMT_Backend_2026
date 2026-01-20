package com.helha.backend.Application.folders.getAll;

import com.helha.backend.Domain.Note;

import java.util.ArrayList;
import java.util.List;

public class GetFoldersTreeOutput {
    public List<Folder> folders = new ArrayList<>();
    public List<Note> rootNotes = new ArrayList<>();

    public static class Folder {
        public long id;
        public String name;
        public Long parentId;

        public List<Note> notes = new ArrayList<>();
        public List<Folder> children = new ArrayList<>();
    }

    public static class Note {
        public long id;
        public String title;
    }
}