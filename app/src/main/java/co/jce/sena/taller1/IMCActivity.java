package co.jce.sena.taller1;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Created by jce on 2/08/15.
 */
public class IMCActivity extends AppCompatActivity {

    //-> Atributos (Componentes)
    private EditText cEstatura,
                     cPeso;
    private Button cCalcular;
    private TextView cDiagnostico;  //: Atributo para Test (Debe eliminarse una ves se realice el Test con éxito)

    //-> Atributos (Variables)
    private float vEstatura,
                  vPeso;

    //-> Constantes
    private static final float ESTATURA_MAXIMA = ( float ) 2.2,
                               ESTATURA_MINIMA = ( float ) 0.4,
                               PESO_MAXIMO = ( float ) 100.0,
                               PESO_MINIMO = ( float ) 20.2;


    //-> Métodos
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super .onCreate(savedInstanceState);
        setContentView( R .layout .activity_imc );

        //-> Acceder a los componentes del "Activity" haciendo un TypeCast según corresponda.
        cEstatura = ( EditText ) findViewById( R .id .et_Estatura );
        cPeso = ( EditText ) findViewById( R .id .et_Peso );
        cCalcular = ( Button ) findViewById( R .id .btn_CalcularIMC );

        cDiagnostico = ( TextView ) findViewById( R .id .tv_Diagnostico );  //: TEST


        // Manejador de Eventos para el botón "Calcular".
        cCalcular .setOnClickListener( new View .OnClickListener() {
            @Override
            public void onClick( View v ) {
                iniciar( null );
            }
        });

    }

    public void iniciar( View v ) {

        mostrarAlerta( diagnostico( calcular( cEstatura, cPeso ) ), calcular( cEstatura, cPeso ) );


    }

    private double calcular( EditText cEstatura, EditText cPeso ) {

        vEstatura = Float .parseFloat( cEstatura .getText() .toString() );
        vPeso = Float .parseFloat( cPeso .getText() .toString() );

        return vPeso / ( Math .pow( vEstatura, 2 ) );
    }

    private String diagnostico( double imc ) {

        String diagnostico = "";

        if( imc < 16 ) {
            diagnostico = "Delgadez severa.";
        }
        else if( imc < 17 ) {
            diagnostico = "Delgadez moderada.";
        }
        else if( imc < 18.5 ) {
            diagnostico = "Delgadez leve.";
        }
        else if( imc < 25 ) {
            diagnostico = "Normal.";
        }
        else if( imc < 30 ) {
            diagnostico = "Preobeso.";
        }
        else if( imc < 35 ) {
            diagnostico = "Obesidad leve.";
        }
        else if( imc < 40 ) {
            diagnostico = "Obesidad media.";
        }
        else {
            diagnostico = "Obesidad severa.";
        }

        return diagnostico;
    }

    private void mostrarAlerta( String diagnostico, double imc ) {

        AlertDialog.Builder adb_Diagnostico = new AlertDialog.Builder( this );

        adb_Diagnostico .setTitle( "" )
                        .setMessage( "Su IMC es: " + imc + "\n Diagnóstico: " + diagnostico )
                        .setPositiveButton( "Aceptar", new DialogInterface.OnClickListener() {

             @Override
             public void onClick( DialogInterface dialog, int which ) {
                finish();
             }

        }) .show();

    }

}