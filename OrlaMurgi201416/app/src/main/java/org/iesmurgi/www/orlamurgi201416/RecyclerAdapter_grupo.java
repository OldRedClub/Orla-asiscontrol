package org.iesmurgi.www.orlamurgi201416;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


class RecyclerAdapter_grupo extends RecyclerView.Adapter<RecyclerAdapter_grupo.ViewHolder> {

    private static int lastPosition = -1;
    private ArrayList<Grupo> grupos;
    private Context context;

    RecyclerAdapter_grupo(ArrayList<Grupo> grupos, Context context) {
        this.context = context;
        this.grupos = grupos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grupo, parent, false);
        return new ViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String texto = grupos.get(position).getNombre();
        if (texto.length() > 10) {
            holder.grupo.setTextSize(20);
        }
        holder.grupo.setText(texto);
        Glide.with(context).load(grupos.get(position).getIdDrawable()).into(holder.image);
        setAnimation(holder.cardView, position);
    }

    @Override
    public int getItemCount() {
        return grupos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) { // con esto verificamos si acabamos la lista
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView grupo;
        ImageView image;
        CardView cardView;
        public Context context;

        ViewHolder(View itemView, Context context) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_grupo);
            grupo = (TextView) itemView.findViewById(R.id.tv_grupo);
            image = (ImageView) itemView.findViewById(R.id.iconillo);
            this.context = context;
        }

    }


}
