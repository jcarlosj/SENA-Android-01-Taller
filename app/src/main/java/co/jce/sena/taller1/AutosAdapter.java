package co.jce.sena.taller1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * Created by jce on 16/08/15.
 */
public class AutosAdapter extends ArrayAdapter< Autos > {

    private Context context;
    private int resource;
    private Autos objects[] = null;

    public AutosAdapter( Context context, int resource, Autos objects[] ) {
        super( context, resource, objects );
        this .context = context;        //: Contexto
        this .resource = resource;      //: Identificador del recurso de diseño "layoutResourceId"
        this .objects = objects;        //: Datos
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        View row = convertView;
        AutosHolder holder;

        if( row == null ) {
            LayoutInflater inflater = ( ( Activity ) context ) .getLayoutInflater();
            row = inflater .inflate( resource, parent, false );

            holder = new AutosHolder();
            holder .ivImagen = ( ImageView ) row .findViewById( R .id .iv_auto );
            holder .tvEscuderia = ( TextView ) row .findViewById( R .id .tv_Escuderia );
            holder .tvBase = ( TextView ) row .findViewById( R .id .tv_Base );
            holder .tvJefe = ( TextView ) row .findViewById( R .id .tv_JefeEquipo );
            row .setTag( holder );
        }
        else {
            holder = ( AutosHolder ) row .getTag();
        }

        //-> Asignamos la posición al elemento.
        Autos autos = objects[ position ];
        holder .ivImagen .setImageResource( autos .vIconAuto );
        holder .tvEscuderia .setText( autos .vEscuderia );
        holder .tvBase .setText( autos .vBase );
        holder .tvJefe .setText( autos .vJefeEquipo );

        return row;
    }

    //-> Creamos un "Holder" para mantener los datos.
    static class AutosHolder {
        //-> Declaramos los datos que deseamos mantener.
        ImageView ivImagen;
        TextView tvEscuderia,
                 tvBase,
                 tvJefe;
    }

}
