package co.jce.sena.taller1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/*
 * Created by jce on 2/08/15.
 */
public class BienesRaicesActivity extends AppCompatActivity {

    //-> Atributos (Componentes)
    private EditText cNombres,
                     cIngresoMensual,
                     cValorDelInmueble;
    private Button cCalcular;

    //-> Atributos (Variables)
    private String vNombres,
                   vMensaje;
    private int vIngresoMensual,
                vValorDelInmueble,
                vNumeroCoutas;
    private double vCuotaInicial,
                  vCuotaMensual;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super .onCreate( savedInstanceState );
        setContentView(R.layout.activity_bienes_raices);

        vMensaje = "\n\nSu financiación ha sido proyectada de la siguiente manera: ";

        //-> Acceder a los componentes del "Activity" haciendo un TypeCast según corresponda.
        cNombres = ( EditText ) findViewById( R .id .et_Nombres );
        cIngresoMensual = ( EditText ) findViewById( R .id .et_IngresoMensual );
        cValorDelInmueble = ( EditText ) findViewById( R .id .et_ValorDelInmueble );
        cCalcular = ( Button ) findViewById( R .id .btn_CalcularFinaciacion );

        // Manejador de Eventos para el botón "Calcular Cuota Inicial y Cuotas Mensuales".
        cCalcular .setOnClickListener( new View .OnClickListener() {
            @Override
            public void onClick( View v ) {
                iniciar( null );
            }
        });

    }

    public void iniciar( View v ) {

        calcular();
        mostrarAlerta();

    }

    private void calcular() {

        vIngresoMensual = Integer .parseInt( cIngresoMensual .getText() .toString() );
        vValorDelInmueble = Integer .parseInt( cValorDelInmueble .getText() .toString() );

        //-> Si el ingreso mensual del comprador es menos de $600000 la cuota inicial será igual al 15% del costo de la casa y el resto se distribuirá en 120 cuotas mensuales.
        if( vIngresoMensual < 600000 ) {
            vCuotaInicial = vValorDelInmueble * 0.15;
            vCuotaMensual = ( vValorDelInmueble - vCuotaInicial ) / 120;
            vMensaje += "\n\nCuota Inicial de: $" + vCuotaInicial + "\ny 120 cuotas de: $" + vCuotaMensual;

        }
        //-> Pero, si el ingreso mensual del comprador es mayor o igual a $1000000 la cuota inicial será igual al 30% del costo de la casa y el resto se distribuirá en 75 cuotas mensuales.
        else if( vIngresoMensual < 1000000 ) {
            vCuotaInicial = vValorDelInmueble * 0.3;
            vCuotaMensual = ( vValorDelInmueble - vCuotaInicial ) / 75;
            vMensaje += "\n\nCuota Inicial de: $" + vCuotaInicial + "\ny 75 cuotas de: $" + vCuotaMensual;
        }
        else {
            vMensaje = "\n\nLamentamos informar que Ud. no puede aplicar por no cumplir con los requisitos para la financiación de vivienda de interés social.";
        }

    }

    private void mostrarAlerta() {

        AlertDialog.Builder adb_Cuotas = new AlertDialog.Builder( this );

        adb_Cuotas .setTitle( "Financiación" )
                .setMessage("Sr. " + cNombres.getText().toString() + vMensaje )
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which ) {
                        finish();
                    }

                }) .show();

    }

}
