package org.iesmurgi.www.orlamurgi201416;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

class Adapter_Grid extends BaseAdapter {

    private ArrayList<Alumnos> alumnos;
    private Context context;
    String id;

    Adapter_Grid(Context context, ArrayList<Alumnos> alumnos, String id) {
        this.alumnos = alumnos;
        this.context = context;
        this.id = id;
    }

    @Override
    public int getCount() {
        return alumnos.size();
    }

    @Override
    public Object getItem(int position) {
        return alumnos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.cardview_grid_alumno, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.icono = (com.mikhaellopez.circularimageview.CircularImageView) convertView.findViewById(R.id.grid_foto_alumno);
            viewHolder.apellidos = (TextView) convertView.findViewById(R.id.grid_apellido_alumno);
            viewHolder.nombre = (TextView) convertView.findViewById(R.id.grid_nombre_alumno);
            viewHolder.cardView = (CardView) convertView.findViewById(R.id.grid_cardview);
            convertView.setTag(viewHolder);

           /*viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(getImageId(context, alumnos.get(position).getThumbnail())).into(viewHolder.icono);
        viewHolder.apellidos.setText(alumnos.get(position).getApellidos());
        viewHolder.nombre.setText(alumnos.get(position).getNombre());

        return convertView;
    }

    private int getImageId(Context context, String imageName) {
        String identificador = "drawable/" + imageName;
        return context.getResources().getIdentifier(identificador, null, context.getPackageName());
    }

    private static class ViewHolder {
        com.mikhaellopez.circularimageview.CircularImageView icono;
        TextView apellidos;
        TextView nombre;
        CardView cardView;
    }
}

