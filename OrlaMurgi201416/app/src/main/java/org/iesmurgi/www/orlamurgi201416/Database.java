package org.iesmurgi.www.orlamurgi201416;

import java.util.ArrayList;

class Database {
    static ArrayList<Cursos> listaCursos() {

        ArrayList<Cursos> items = new ArrayList<>();

        items.add(new Cursos("Bachillerato", R.drawable.cursos, lista_grupos_bach()));
        items.add(new Cursos("Grado Medio", R.drawable.cursos, lista_grupos_GM()));
        items.add(new Cursos("Grado Superior", R.drawable.cursos, lista_grupos_GS()));
        items.add(new Cursos("Formación Profesional Básica", R.drawable.cursos, lista_grupos_FPB()));

        return new ArrayList<>(items);
    }

    private static ArrayList<Grupo> lista_grupos_bach() {

        ArrayList<Grupo> items = new ArrayList<>();

        items.add(new Grupo("BACHA", "Bachillerato", "Grupo A", R.drawable.bacha));
        items.add(new Grupo("BACHB", "Bachillerato", "Grupo B", R.drawable.bachb));
        items.add(new Grupo("BACHC", "Bachillerato", "Grupo C", R.drawable.bachc));


        return new ArrayList<>(items);
    }

    private static ArrayList<Grupo> lista_grupos_GM() {

        ArrayList<Grupo> items = new ArrayList<>();

        items.add(new Grupo("SMR", "Grado Medio", "SMR", R.drawable.smr));
        items.add(new Grupo("GAD", "Grado Medio", "GAD", R.drawable.images));
        items.add(new Grupo("IEA", "Grado Medio", "IEA", R.drawable.iea));
        items.add(new Grupo("EVA", "Grado Medio", "EVA", R.drawable.eva));


        return new ArrayList<>(items);
    }

    private static ArrayList<Grupo> lista_grupos_GS() {

        ArrayList<Grupo> items = new ArrayList<>();

        items.add(new Grupo("DAM", "Grado Superior", "DAM", R.drawable.damgrupo));
        items.add(new Grupo("FIN", "Grado Superior", "FIN", R.drawable.fin));
        items.add(new Grupo("SEA", "Grado Superior", "SEA", R.drawable.sea));


        return new ArrayList<>(items);
    }

    private static ArrayList<Grupo> lista_grupos_FPB() {

        ArrayList<Grupo> items = new ArrayList<>();

        items.add(new Grupo("FPB", "Formación Profesional Básica", "Mant. de Vehíc.", R.drawable.images));

        return new ArrayList<>(items);
    }

    /*public static ArrayList<Alumnos> getpruebaGrid(){
        ArrayList<Alumnos>aux=new ArrayList<>();
        aux.add(new Alumnos("BACHA1","Alcalá Gómez","Jose Carlos","BACH","A","/BACHA/bacha1.JPG",R.drawable.bacha_alcala_gomez__jose_carlos));
        aux.add(new Alumnos("BACHA2","Barnes Ortiz","Marina","BACH","A","/BACHA/bacha2.JPG",R.drawable.bacha_barnes_ortiz__marina));
        aux.add(new Alumnos("BACHA3","Becerra Campos","Tania M.","BACH","A","/BACHA/bacha3.JPG",R.drawable.bacha_becerra_campos__tania_m_));
        aux.add(new Alumnos("BACHA4","Bozdoc","Amalia Codruta Florina","BACH","A","/BACHA/bacha4.JPG",R.drawable.bacha_bozdoc__amalia_codruta_florina));
        aux.add(new Alumnos("BACHA5","Chervonnyy","Nikita","BACH","A","/BACHA/bacha5.JPG",R.drawable.bacha_chervonnyy__nikita));
        aux.add(new Alumnos("BACHA6","Copado López","Carmen","BACH","A","/BACHA/bacha6.JPG",R.drawable.bacha_copado_lopez__carmen));

        return aux;
    }*/

}
