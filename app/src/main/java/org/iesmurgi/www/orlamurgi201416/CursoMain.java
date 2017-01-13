package org.iesmurgi.www.orlamurgi201416;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;

public class CursoMain extends AppCompatActivity {

    private ArrayList<Cursos> listaCursos;
    private CollapsingToolbarLayout micollapse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_curso);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        com.mikhaellopez.circularimageview.CircularImageView img;
        img = (com.mikhaellopez.circularimageview.CircularImageView) findViewById(R.id.logo_murgi_desglose_ppal);
        RecyclerView recyclerView;
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        micollapse = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout_curso);
        Glide.with(this).load(R.drawable.fondonegro).asBitmap().into(new SimpleTarget<Bitmap>(800, 600) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(getResources(), resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    micollapse.setBackground(drawable);
                }
            }
        });
        Glide.with(this).load(R.drawable.logo_murgi).into(img);

        listaCursos = Database.listaCursos();
        RecyclerAdapter adaptador;
        adaptador = new RecyclerAdapter(listaCursos, this);

        recyclerView.setAdapter(adaptador);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ir(listaCursos.get(position), view);//Nos lleva al activity desglose de grupos

            }
        }));
    }

    protected void ir(Cursos curso, View view) {
        Intent intent = new Intent(CursoMain.this, Desglose_Grupos.class);
        intent.putExtra("cursi", curso);
        startActivity(intent);
    }

}
