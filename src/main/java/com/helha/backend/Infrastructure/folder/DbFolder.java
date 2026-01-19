package com.helha.backend.Infrastructure.folder;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="folders")
public class DbFolder {
    // identifiant unique du dossier (clé primaire)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    // nom du dossier
    // @Column permet de configurer la colonne en base
    // nullable = false correspond à not null en sql
    @Column(nullable = false)
    public String name;

    @Column(name="id_user", nullable=false)
    // name = id_user permet de lier ce champ à la colonne id_user en base
    // on stocke l'id de l'utilisateur propriétaire
    public long userId;

    // signifie que plusieurs dossiers peuvent avoir le même parent
    @ManyToOne
    @JoinColumn(name="parent_id")
    // ici, parent_id représente un autre dossier
    private DbFolder parent;

    // inverse de OneToMany
    // un dossier peut avoir plusieurs sous-dossiers
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<DbFolder> children;

    // getters / setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public DbFolder getParent() {
        return parent;
    }

    public void setParent(DbFolder parent) {
        this.parent = parent;
    }

    public List<DbFolder> getChildren() {
        return children;
    }

    public void setChildren(List<DbFolder> children) {
        this.children = children;
    }
}
