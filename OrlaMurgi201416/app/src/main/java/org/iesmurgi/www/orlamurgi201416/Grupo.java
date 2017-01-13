package org.iesmurgi.www.orlamurgi201416;

import java.io.Serializable;
import java.util.ArrayList;


class Grupo implements Serializable {
    private String ID;
    private String curso;
    private String nombre;
    private int idDrawable;
    private ArrayList<Alumnos> listaAlumnos;

    Grupo(String ID, String curso, String no, int idDrawable) {
        this.nombre = no;
        this.idDrawable = idDrawable;
        listaAlumnos = new ArrayList<>();
        this.curso = curso;
        this.ID = ID;
    }

    String getNombre() {
        return nombre;
    }

    int getIdDrawable() {
        return idDrawable;
    }

    String getID() {
        return ID;
    }

}
