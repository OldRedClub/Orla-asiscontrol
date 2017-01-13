package org.iesmurgi.www.orlamurgi201416;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

class ConexionDB extends AsyncTask<String, Void, ResultSet> {

    LinearLayout layout;
    private CoordinatorLayout clayout;
    private Activity activity;
    private AlertDialog progressdialog;
    private GridView gridView;
    private String ID;

    ConexionDB(Activity activity, LinearLayout layout, AlertDialog dialog, GridView gridView, CoordinatorLayout clayout) {
        this.activity = activity;
        this.layout = layout;
        this.progressdialog = dialog;
        this.gridView = gridView;
        this.clayout = clayout;
    }

    @Override
    protected ResultSet doInBackground(String... strings) {
        ID = strings[0];
        try {
            Connection conn;
            Class.forName("com.mysql.jdbc.Driver");
            String rutaINI;
            if (ActividadPrincipal.usuarioF.equals("admin")) {
                rutaINI = "10.10.4.150";
            } else {
                rutaINI = "www.iesmurgi.org";
            }
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + rutaINI + "/usuario2", "invitado", "pinvitado");
            Statement estado = (Statement) conn.createStatement();
            String peticion = "select * from " + strings[0] + " order by apellidos";
            return estado.executeQuery(peticion);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ResultSet result) {
        try {
            if (result == null) {
                throw new Exception();
            }
            int cont = 0;

            while (result.next()) {
                Alumnos aux = new Alumnos(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));
                Grid_alumnos.arrayAlumnos.add(aux);
                cont++;
            }
            gridView.setAdapter(new Adapter_Grid(activity, Grid_alumnos.arrayAlumnos, ID));
            Grid_alumnos.resultOK = true;//liberamos bucle
            if (cont == 0) {
                progressdialog.dismiss();
                Toast.makeText(activity, "No hay datos", Toast.LENGTH_SHORT).show();
            }

            progressdialog.dismiss();
        } catch (Exception e) {
            progressdialog.dismiss();

            if (layout == null) {
                Snackbar snackbar = Snackbar
                        .make(clayout, "Necesitas conexión a Internet", Snackbar.LENGTH_LONG)
                        .setAction("HABILITAR WIFI", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //PARA ACTIVAR WIFI
                                WifiManager wifi = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
                                wifi.setWifiEnabled(true); // true or false to activate/deactivate wifi

                                //REINICIO ACTIVITY
                                Intent i = activity.getPackageManager()
                                        .getLaunchIntentForPackage(activity.getPackageName());
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                activity.startActivity(i);
                            }
                        });

                snackbar.show();
            } else {
                Snackbar snackbar = Snackbar
                        .make(layout, "Necesitas conexión a Internet", Snackbar.LENGTH_LONG)
                        .setAction("HABILITAR WIFI", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //PARA ACTIVAR WIFI
                                WifiManager wifi = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
                                wifi.setWifiEnabled(true); // true or false to activate/deactivate wifi

                                //REINICIO ACTIVITY
                                Intent i = activity.getPackageManager()
                                        .getLaunchIntentForPackage(activity.getPackageName());
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                activity.startActivity(i);
                            }
                        });

                snackbar.show();
            }


        }

    }
}
