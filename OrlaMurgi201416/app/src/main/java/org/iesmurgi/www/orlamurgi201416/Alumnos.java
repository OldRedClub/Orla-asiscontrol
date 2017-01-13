package org.iesmurgi.www.orlamurgi201416;

import java.io.Serializable;

class Alumnos implements Serializable {
    private String ID;
    private String apellidos;
    private String nombre;
    private String CURSO;
    private String GRUPO;
    private String RUTA;
    private String thumbnail;

    Alumnos(String ID, String apellidos, String nombre, String CURSO, String GRUPO, String RUTA, String iDrawable) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ID = ID;
        this.thumbnail = iDrawable;
        this.GRUPO = GRUPO;
        this.CURSO = CURSO;
        this.RUTA = RUTA;
    }

    String getNombre() {
        return nombre;
    }

    String getApellidos() {
        return apellidos;
    }

    String getThumbnail() {
        return thumbnail;
    }

    String getID() {
        return ID;
    }

    String getGRUPO() {
        return GRUPO;
    }

}
