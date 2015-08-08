package co.jce.sena.taller1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

/*
 * Created by jce on 2/08/15.
 */
public class GetActivity extends AppCompatActivity {

    //-> Atributos (Componentes)
    private EditText cEdad,
                     cPeso,
                     cEstatura;
    private RadioButton cGeneroMasculino, cGeneroFemenino;
    private Spinner cActividadFisica;
    private Button cCalcular;

    //-> Atributos (Variables)
    private String vNombres, vActividadFisica[];
    private int vEdad;
    private float vPeso, vEstatura;

    //-> CONSTANTES de la ecuación para cada uno de los géneros.
    private static final double HOMBRE[] = { 66.5, 13.7, 5.0, 6.8 },
                                MUJER[] = { 65.1, 9.56, 1.85, 4.7 };

    private ArrayAdapter< String > adaptador;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super .onCreate(savedInstanceState);
        setContentView( R .layout .activity_get );

        //-> Declaración de "Array" de Actividades Físicas.
        vActividadFisica = new String[] { "Seleccionar..", "Sedentario", "Actividad ligera", "Actividad moderada", "Actividad intensa", "Actividad muy intensa", "Acción dinámica específica" };

        //-> Acceder a los componentes del "Activity" haciendo un TypeCast según corresponda.
        cEdad = ( EditText ) findViewById( R .id .et_Edad );
        cPeso = ( EditText ) findViewById( R .id .et_Peso );
        cEstatura = ( EditText ) findViewById( R .id .et_Estatura );
        cGeneroMasculino = ( RadioButton ) findViewById( R .id .rb_Masculino );
        cGeneroFemenino = ( RadioButton ) findViewById( R .id .rb_Femenino );
        cActividadFisica = ( Spinner ) findViewById( R .id .spn_ActividadFisica );

        cCalcular = ( Button ) findViewById( R .id .btn_CalcularGET );

        //-> Datos > Adaptador > Spinner
        adaptador = new ArrayAdapter<String>( GetActivity.this, android .R .layout .simple_list_item_1, vActividadFisica );
        cActividadFisica .setAdapter( adaptador );

        // Manejador de Eventos para el botón "Calcular".
        cCalcular .setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                iniciar( null );
            }
        });

    }

    public void iniciar( View v ) {

        vEdad = Integer .parseInt(cEdad.getText().toString());
        vEstatura = Float .parseFloat(cEstatura.getText().toString());
        vPeso = Float .parseFloat( cPeso .getText() .toString() );

        mostrarAlerta( actividad_fisica( getCalculoGenero() ) );

    }

    private double calculo( double CONST[] ) {

        //-> Fórmula para Hombres: 
        //   GET = 66,5 + (13,7 X P) + (5 X E) - (6,8 X I) = Kcal/día.
        //-> Fórmula para Mujeres:
        //    GET = 65,1 + (9,56 X P) + (1,85 X E) - (4,7 X I) = Kcal/día.

        return CONST[ 0 ] + CONST[ 1 ] * vPeso + CONST[ 2 ] * vEstatura - CONST[ 3 ] * vEdad;
    }

    private double getCalculoGenero() {

        double get = 0.0;

        if( cGeneroFemenino .isChecked() ) {
            get = calculo( MUJER );
        }
        else if( cGeneroMasculino .isChecked() ) {
            get = calculo( HOMBRE );
        }

        return get;
    }

    private double actividad_fisica( double resultado ) {

        float incremento = ( float ) 0.0;

        if( cActividadFisica .getSelectedItem() .toString() .equals("Sedentario") ) {
            incremento = ( float ) 0.2;
        }
        else if( cActividadFisica .getSelectedItem() .toString() .equals("Actividad ligera") ) {
            incremento = ( float ) 0.3;
        }
        else if( cActividadFisica .getSelectedItem() .toString() .equals( "Actividad moderada" ) ) {
            incremento = ( float ) 0.4;
        }
        else if( cActividadFisica .getSelectedItem() .toString() .equals( "Actividad intensa" ) ) {
            incremento = ( float ) 0.5;
        }
        else if( cActividadFisica .getSelectedItem() .toString() .equals( "Actividad muy intensa" ) ) {
            incremento = ( float ) 0.7;
        }
        else if( cActividadFisica .getSelectedItem() .toString() .equals( "Acción dinámica específica" ) ) {
            incremento = ( float ) 0.1;
        }

        resultado += resultado * incremento;

        return resultado;
    }

    private void mostrarAlerta( double get ) {

        AlertDialog.Builder adb_Diagnostico = new AlertDialog.Builder( this );

        adb_Diagnostico .setTitle( "Gasto Energético Total (GET)" )
                .setMessage( "Su GET es: " + get )
                .setPositiveButton( "Aceptar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick( DialogInterface dialog, int which ) {
                        finish();
                    }

                }) .show();

    }

}
