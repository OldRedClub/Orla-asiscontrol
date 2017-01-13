package org.iesmurgi.www.orlamurgi201416;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadPrincipal extends AppCompatActivity {

    public static String usuarioF;
    public static String passF;
    EditText et1;
    EditText et2;
    FloatingActionButton fab;
    Button ib;
    String usu = "bach16";
    String usu2 = "med16";
    String usu3 = "sup16";
    String usu4 = "fpb16";
    String usu5 = "admin";
    String pwd = "bach201416";
    String pwd2 = "med201416";
    String pwd3 = "sup201416";
    String pwd4 = "fpb201416";
    String pwd5 = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Desarrolladores");
        alertDialog.setMessage("Alfonso D. Clement Paredes\nEmail: alfonsodavidclement@gmail.com\n\nJuan Francisco Lirola García\nEmail: lirolajf@gmail.com\n" +
                "\n" +
                "Arturo Gómez Morales\n" +
                "Email: arturogomezmorales1994@gmail.com");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        et1 = (EditText) findViewById(R.id.User);
        et2 = (EditText) findViewById(R.id.Password);
        ib = (Button) findViewById(R.id.botonCursos);

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String user = prefs.getString("usuario", "");
        String contra = prefs.getString("pass", "");
        if (!user.isEmpty()) et1.setText(user);
        if (!contra.isEmpty()) et2.setText(contra);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((et1.getText().toString().equals(usu) && et2.getText().toString().equals(pwd)) || (et1.getText().toString().equals(usu2) && et2.getText().toString().equals(pwd2)) || (et1.getText().toString().equals(usu3) && et2.getText().toString().equals(pwd3)) || (et1.getText().toString().equals(usu4) && et2.getText().toString().equals(pwd4)) || (et1.getText().toString().equals(usu5) && et2.getText().toString().equals(pwd5))) {

                    String usu = et1.getText().toString();
                    String pass = et2.getText().toString();

                    usuarioF = usu;
                    passF = pass;

                    SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("usuario", usu);
                    editor.putString("pass", pass);
                    editor.apply();

                    Toast.makeText(ActividadPrincipal.this, "Accediendo...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActividadPrincipal.this, CursoMain.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(ActividadPrincipal.this, "Usuario o Contraseña erróneos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setIcon(R.drawable.brain);
                alertDialog.show();
            }
        });


    }


}
