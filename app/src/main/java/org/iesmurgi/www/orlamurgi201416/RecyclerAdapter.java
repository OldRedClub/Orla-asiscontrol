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

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static int lastPosition = -1;
    private List<Cursos> cursos;
    private Context context;

    public RecyclerAdapter(List<Cursos> cursos, Context context) {
        this.context = context;
        this.cursos = cursos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cursos, parent, false);
        return new ViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.curso.setText(cursos.get(position).getCurso());
        Glide.with(context).load(cursos.get(position).getIdDrawable()).into(holder.image);
        setAnimation(holder.cardView, position);


    }

    @Override
    public int getItemCount() {
        return cursos.size();
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
        TextView curso;
        ImageView image;
        CardView cardView;
        public Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            curso = (TextView) itemView.findViewById(R.id.nombreCurso);
            image = (ImageView) itemView.findViewById(R.id.fotoCurso);
            this.context = context;
        }


    }

}
