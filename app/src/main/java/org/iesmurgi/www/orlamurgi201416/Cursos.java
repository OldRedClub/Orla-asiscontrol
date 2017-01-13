package org.iesmurgi.www.orlamurgi201416;

import java.io.Serializable;
import java.util.ArrayList;

class Cursos implements Serializable {
    private String curso;
    private ArrayList<Grupo> totalGrupos;
    private int idDrawable;

    Cursos(String curso, int idDrawable, ArrayList<Grupo> tot) {
        this.curso = curso;
        this.idDrawable = idDrawable;
        this.totalGrupos = tot;
    }

    ArrayList<Grupo> getTotalGrupos() {
        return totalGrupos;
    }

    String getCurso() {
        return curso;
    }

    int getIdDrawable() {
        return idDrawable;
    }

}
