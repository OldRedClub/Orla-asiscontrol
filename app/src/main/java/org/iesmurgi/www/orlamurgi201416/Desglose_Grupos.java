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

public class Desglose_Grupos extends AppCompatActivity {

    ArrayList<Grupo> lista_grupos;
    private CollapsingToolbarLayout collapse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desglose__grupos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_desglose_grupos);
        setSupportActionBar(toolbar);

        //ponemos de forma eficiente el background del collapsing toolbar layout
        collapse = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        Glide.with(this).load(R.drawable.fondonegro).asBitmap().into(new SimpleTarget<Bitmap>(800, 600) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(getResources(),resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    collapse.setBackground(drawable);
                }
            }
        });

        //ponemos de forma eficiente el icono
        com.mikhaellopez.circularimageview.CircularImageView circular;
        circular = (com.mikhaellopez.circularimageview.CircularImageView) findViewById(R.id.logo_murgi_desglose);
        Glide.with(this).load(R.drawable.logo_murgi).into(circular);

        Cursos cur_seleccionado = (Cursos) getIntent().getSerializableExtra("cursi");

        setTitle(cur_seleccionado.getCurso());

        lista_grupos = cur_seleccionado.getTotalGrupos();

        RecyclerView recyclerView;

        recyclerView = (RecyclerView) findViewById(R.id.rv_desglose_grupos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerAdapter_grupo adaptador;
        adaptador = new RecyclerAdapter_grupo(lista_grupos, this);

        recyclerView.setAdapter(adaptador);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Grupo grupo_sel = lista_grupos.get(position);

                Intent intent = new Intent(Desglose_Grupos.this, Grid_alumnos.class);
                intent.putExtra("grupo", grupo_sel);
                startActivity(intent);

            }
        }));


    }


}
