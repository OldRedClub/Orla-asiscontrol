package org.iesmurgi.www.orlamurgi201416;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import dmax.dialog.SpotsDialog;

public class Detalle_alumno extends AppCompatActivity {

    AlertDialog dialogo;
    ImageView imageView;
    TextView apellidos;
    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alumno);
        dialogo = new SpotsDialog(this, "Cargando...");
        imageView = (ImageView) findViewById(R.id.foto_detalle_alumno);
        apellidos = (TextView) findViewById(R.id.apellidos_detalle_alumno);
        nombre = (TextView) findViewById(R.id.nombre_detalle_alumno);
        Alumnos alumno = (Alumnos) getIntent().getSerializableExtra("Alumno");
        loadPhoto(alumno);

    }

    protected void loadPhoto(Alumnos alumno) {
        GetImageFromStorage gifs = new GetImageFromStorage();//creo y lanzo hilo para rescatar imagen de memoria dispositivo
        Bitmap image = gifs.doInBackground(alumno.getID());

        if (image == null) {
            // No existe y la descargamos
            dialogo.show();
            new DownloadImageTask(this, imageView, alumno, dialogo).execute("");
        } else {
            //Existe y la rescatamos
            //Glide.with(this).load(image).asBitmap().into(imageView);
            imageView.setImageBitmap(image);
            //  Glide.with(this).load(image).asBitmap().into(imageView);
        }
        String apellidosAlumno = alumno.getApellidos() + ",";
        apellidos.setText(apellidosAlumno);
        nombre.setText(alumno.getNombre());

    }

    public class DownloadImageTask extends AsyncTask<String, Bitmap, Bitmap> {

        ImageView imageView;
        Alumnos alumno;
        AlertDialog dialog;
        Context context;

        DownloadImageTask(Context context, ImageView imageView, Alumnos alumno, AlertDialog dialog) {
            this.context = context;
            this.imageView = imageView;
            this.alumno = alumno;
            this.dialog = dialog;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String aux;
            if (ActividadPrincipal.usuarioF.equals("admin")) {
                aux = "10.10.4.150";
            } else {
                aux = "www.iesmurgi.org:85";
            }

            String ruta = "http://" + aux + "/segundodam/" + alumno.getGRUPO() + "/" + alumno.getID() + ".JPG";
            Bitmap mIcon11;
            try {
                URL myURL = new URL(ruta);
                InputStream in = myURL.openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                return mIcon11;
            } catch (Exception e) {
                e.printStackTrace();
            }
            dialog.dismiss();
            return null;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                imageView.setImageBitmap(result);
                dialog.dismiss();//elimino progressdialog
                new SaveImagesOnStorage(result).execute(alumno.getID());
            } else {
                //si devuelve imagen null es porque no lo ha encontrado en la base de datos y por lo tanto tiene foto genérica
                dialog.dismiss();//elimino progressdialog
            }
        }
    }

    //hilo a parte para guardar imagen en memoria de dispositivo
    private class SaveImagesOnStorage extends AsyncTask<String, Void, Void> {

        FileOutputStream fos = null;
        Bitmap image;

        SaveImagesOnStorage(Bitmap image) {
            this.image = image;
        }

        protected Void doInBackground(String... name) {
            try {
                //abro o creo (Segun exista o no) el archivo y le meto la imagen en bytes
                fos = openFileOutput(name[0], Context.MODE_PRIVATE);
                fos.write(Utility.getBytes(image));
                fos.close();//cierro flujo

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    //hilo para recuperar una imagen de la memoria interna
    private class GetImageFromStorage extends AsyncTask<String, Void, Bitmap> {

        FileInputStream fis = null;

        protected Bitmap doInBackground(String... id) {

            try {
                fis = openFileInput(id[0]);
                byte[] aux = new byte[1048576];
                while (fis.read(aux) != -1);

                return Utility.getPhoto(aux);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

}
