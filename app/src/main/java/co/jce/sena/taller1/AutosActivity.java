package co.jce.sena.taller1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

/*
 * Created by jce on 2/08/15.
 */
public class AutosActivity extends AppCompatActivity{

    //-> Atributos (Componentes)
    ListView vAutos;
    View encabezado;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_autos);

        //-> Array de datos de tipo "AutosF1" que se desplegarán en el "ListView".
        Autos datos_autos[] = new Autos[] {
            new Autos( R .drawable .ic_ferrari, "Scuderia Ferrari", "Maranello, Italy", "Maurizio Arrivabene" ),
            new Autos( R. drawable .ic_forceindia, "Sahara Force India F1 Team", " Silverstone, United Kingdom", "Vijay Mallya" ),
            new Autos( R .drawable .ic_lotus, "Lotus F1 Team", "Enstone, United Kingdom", "Gerard Lopez" ),
            new Autos( R .drawable .ic_marussia, "Manor Marussia F1 Team", "Dinnington, United Kingdom", "John Booth" ),
            new Autos( R .drawable .ic_mclaren, "McLaren Honda", "Woking, United Kingdom", "Eric Boullier" ),
            new Autos( R .drawable .ic_mercedes, "Mercedes AMG Petronas Formula One Team", "Brackley, United Kingdom", "Toto Wolff" ),
            new Autos( R .drawable .ic_redbull, "Infiniti Red Bull Racing", "Milton Keynes, United Kingdom", "Christian Horner" ),
            new Autos( R .drawable .ic_sauber, "Sauber F1 Team", "Hinwil, Switzerland", "Monisha Kaltenborn" ),
            new Autos( R .drawable .ic_tororosso, "Scuderia Toro Rosso", "Faenza, Italy", "Franz Tost" ),
            new Autos( R .drawable .ic_williams, "Williams Martini Racing", "Grove, United Kingdom", "Frank Williams" )
        };

        //-> Instanciamos el Adaptador "AutoAdapter"
        //   Le pasamos el contexto, la vista para el "item" o fila y el "array" con los datos.
        AutosAdapter adapter = new AutosAdapter( this, R .layout .listview_item_row, datos_autos );
        vAutos = ( ListView ) findViewById( R .id .lv_Autos );      //: Accedemos al componente "ListView" del "Activity"
        encabezado = getLayoutInflater() .inflate( R.layout.list_header_row, null );     //: Traigo el encabezado / Quité el TypeCast (View)
        vAutos .addHeaderView( encabezado );          //: Agregamos el encabezado al "ListView"
        vAutos .setAdapter( adapter );                //: Agregamos la lista de datos al "ListView" a través del adaptador.

    }
}
