package org.iesmurgi.www.orlamurgi201416;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class Grid_alumnos extends AppCompatActivity {

    public static ArrayList<Alumnos> arrayAlumnos;
    public static boolean resultOK;
    GridView gridview;
    CoordinatorLayout layout;
    private CollapsingToolbarLayout collapse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_alumnos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_grid_alumnos);
        setSupportActionBar(toolbar);

        layout = (CoordinatorLayout) findViewById(R.id.coordinator_grid);
        arrayAlumnos = new ArrayList<>();
        AlertDialog progressDialog;
        progressDialog = new SpotsDialog(this, "Cargando...");
        gridview = (GridView) findViewById(R.id.grid_view_alumnos);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ViewCompat.setNestedScrollingEnabled(gridview, true);
        }

        Grupo grupo = (Grupo) getIntent().getSerializableExtra("grupo");
        setTitle(grupo.getNombre());
        collapse = (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout_grid);
        Glide.with(this).load(grupo.getIdDrawable()).asBitmap().into(new SimpleTarget<Bitmap>(800, 600) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(getResources(), resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    collapse.setBackground(drawable);
                }
            }
        });

        progressDialog.show();//mostramos dialogo de carga
        resultOK = false;
        new ConexionDB(this, null, progressDialog, gridview, layout).execute(grupo.getID());//hago una consulta a mysql a la tabla que coincide con el ID

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Grid_alumnos.this, Detalle_alumno.class);
                intent.putExtra("Alumno", arrayAlumnos.get(position));
                Grid_alumnos.this.startActivity(intent);
            }
        });
    }

}
