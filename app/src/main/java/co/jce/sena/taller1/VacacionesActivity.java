package co.jce.sena.taller1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/*
 * Created by jce on 2/08/15.
 */
public class VacacionesActivity extends AppCompatActivity {

    //-> Atributos (Componentes)
    private EditText cNombres,
                     cAntiguedad,
                     cEdad;
    private RadioButton cDirectivoSi,
                        cDirectivoNo;
    private Button cCalcular;

    //-> Atributos (Variables)
    private String vNombres;
    private float vAntiguedad;
    private int vEdad, vDiasVacaciones;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacaciones);

        //-> Dias de vacaciones inicializa en CERO.
        vDiasVacaciones = 0;

        //-> Acceder a los componentes del "Activity" haciendo un TypeCast según corresponda.
        cNombres = ( EditText ) findViewById( R .id .et_Nombres );
        cAntiguedad = ( EditText ) findViewById( R .id .et_Antiguedad );
        cEdad = ( EditText ) findViewById( R .id .et_Edad );
        cDirectivoSi = ( RadioButton ) findViewById( R .id .rb_DirectivoSi );
        cDirectivoNo = ( RadioButton ) findViewById( R .id .rb_DirectivoNo );
        cCalcular = ( Button ) findViewById( R .id .btn_CalcularPago );

        // Manejador de Eventos para el botón "Calcular".
        cCalcular .setOnClickListener( new View .OnClickListener() {
            @Override
            public void onClick( View v ) {
                iniciar( null );
            }
        });

    }

    public void iniciar( View v ) {

        mostrarAlerta( calcular() );
    }

    private int calcular() {

        vAntiguedad = Float.parseFloat(cAntiguedad.getText() .toString() );
        vEdad = Integer .parseInt( cEdad .getText() .toString() );

        //-> Si una persona lleva menos de un año en la empresa, tiene derecho a dos días por cada mes de presencia, si no, al menos a 28 días.
        if( vAntiguedad < 12 ) {
            vDiasVacaciones = ( ( int ) vAntiguedad / 2 ) * 2;
        }
        else {
            vDiasVacaciones = 28;
        }

        //-> Si es un directivo y si tiene menos de 35 años y si su antigüedad es superior a 3 años, obtiene 2 días suplementarios.
        if( cDirectivoSi .isChecked() ) {
            if( vEdad < 35 && vAntiguedad > 3 ) {
                vDiasVacaciones += 2;
            }
        }
        else if( cDirectivoNo .isChecked() ) {
            //-> Si tiene menos de 45 años y si su antigüedad es superior a los 5 años, se le conceden 4 días suplementarios.
            if ( vEdad < 45 && vAntiguedad > 5) {
                vDiasVacaciones += 4;
            }

        }

        return vDiasVacaciones;
    }

    private void mostrarAlerta( int diasVacaciones ) {

        AlertDialog.Builder adb_DiasVacaciones = new AlertDialog.Builder( this );

        adb_DiasVacaciones .setTitle( "Días de Vacaciones" )
                .setMessage( "Sr. " + cNombres .getText() .toString() + "\n\nSe le autorizan " + diasVacaciones + " días de vacaciones" )
                .setPositiveButton( "Aceptar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick( DialogInterface dialog, int which ) {
                        finish();
                    }

                }) .show();

    }

}
