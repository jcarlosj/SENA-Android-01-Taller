package co.jce.sena.taller1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Created by jce on 2/08/15.
 */
public class CalculadoraActivity extends AppCompatActivity implements View.OnClickListener {

    //-> Atributos (Componentes)
    private TextView cPantalla,
                     cPantallaAuxiliar;
    private Button cCero, cUno, cDos, cTres, cCuatro, cCinco, cSeis, cSiete, cOcho, cNueve,
                   cSuma, cResta, cMultiplica, cDivide, cCuadrado, cRaiz,
                   cPunto, cIgual, cDEL, cAC;

    //-> Atributos (Variables)
    private Boolean vPunto,
                    vIniciaOperacion,
                    vCapturaPrimerValor,
                    vCapturaSegundoValor,
                    vDEL,
                    vAC,
                    vIgual;

    private double vTemporal,
                   vFinal;
    private String vEntrada,
                   vOperacionPrincipal,
                   vOperacionTemporal,
                   vResultado;

    //-> CONSTANTES de la ecuación para cada uno de los géneros.
    private static final String NUMEROS[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
    private static final String PUNTO = ".";
    private static final String BORRAR[] = { "total", "pantalla" };

    public CalculadoraActivity() {
        vPunto = false;
        vIniciaOperacion = false;
        vCapturaPrimerValor = false;
        vCapturaSegundoValor = false;
        vDEL = false;
        vAC = false;
        vIgual = false;
        vEntrada = "";
        vResultado = "";
    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        componentes();

    }

    @Override
    public void onClick(View v) {

        //-> Accedemos al componente de la pantalla.
        //   Hacemos un TypeCast para un TextView
        cPantalla = ( TextView ) findViewById( R .id .tv_Pantalla );
        cPantalla .setOnClickListener(this);

        try {
            //-> Componentes que hace referencia al teclado númerico ( 0 - 9 ).
            if( v .getId() == R .id .btn_Cero ) {
                insertar( NUMEROS[ 0 ] );
            }
            if( v .getId() == R .id .btn_Uno ) {
                insertar( NUMEROS[ 1 ] );
            }
            if( v .getId() == R .id .btn_Dos ) {
                insertar( NUMEROS[ 2 ] );
            }
            if( v .getId() == R .id .btn_Tres ) {
                insertar( NUMEROS[ 3 ] );
            }
            if( v .getId() == R .id .btn_Cuatro ) {
                insertar( NUMEROS[ 4 ] );
            }
            if( v .getId() == R .id .btn_Cinco ) {
                insertar( NUMEROS[ 5 ] );
            }
            if( v .getId() == R .id .btn_Seis ) {
                insertar( NUMEROS[ 6 ] );
            }
            if( v .getId() == R .id .btn_Siete ) {
                insertar( NUMEROS[ 7 ] );
            }
            if( v .getId() == R .id .btn_Ocho ) {
                insertar( NUMEROS[ 8 ] );
            }
            if( v .getId() == R .id .btn_Nueve ) {
                insertar( NUMEROS[ 9 ] );
            }

            //-> Componentes que hace referencia al teclado operaciones.
            if( v .getId() == R .id .btn_Suma ) {
                operar("suma");
            }
            if( v .getId() == R .id .btn_Resta ) {
                operar("resta");
            }
            if( v .getId() == R .id .btn_Multiplicacion ) {
                operar("multiplicacion");
            }
            if( v .getId() == R .id .btn_Division ) {
                operar( "division");
            }
            if( v .getId() == R .id .btn_Cuadrado ) {
                cuadrado();
            }
            if( v .getId() == R .id .btn_RaizCuadrada ) {
                raiz();
            }

            //-> Componente que hace referencia al igual en el teclado.
            if( v .getId() == R .id .btn_Igual ) {
                vIniciaOperacion = false;
                igual(vOperacionPrincipal);
            }

            //-> Componente que hace referencia al punto en el teclado.
            if( v .getId() == R .id .btn_Decimal ) {

                //-> Valida que una expresión decimal no tenga más de un (.) PUNTO
                if( !vPunto ) {
                    insertar( PUNTO );
                    vPunto = true;
                }

            }

            //-> Componentes que hace referencias a los tipos de borrado de pantalla.
            if( v .getId() == R .id .btn_DEL ) {
                vEntrada = "";
                insertar( NUMEROS[ 0 ] );
                vPunto = false;
                vDEL = true;
            }
            if( v .getId() == R .id .btn_AC ) {
                vEntrada = "";
                insertar( NUMEROS[ 0 ] );
                vPunto = false;
            }

        }
        catch( Exception e ) {
            cPantalla .setText( "Error" );
        }

    }

    private void componentes() {

        //-> Accedemos a los componentes del teclado numérico.
        //   Hacemos un TypeCast para un Button.
        cCero = ( Button ) findViewById( R.id.btn_Cero );
        cCero .setOnClickListener( this );
        cUno = ( Button ) findViewById( R.id.btn_Uno );
        cUno .setOnClickListener( this );
        cDos = ( Button ) findViewById( R.id.btn_Dos );
        cDos .setOnClickListener( this );
        cTres = ( Button ) findViewById( R.id.btn_Tres );
        cTres .setOnClickListener( this );
        cCuatro = ( Button ) findViewById( R.id.btn_Cuatro );
        cCuatro .setOnClickListener( this );
        cCinco  = ( Button ) findViewById( R.id.btn_Cinco );
        cCinco .setOnClickListener( this );
        cSeis   = ( Button ) findViewById( R.id.btn_Seis );
        cSeis .setOnClickListener( this );
        cSiete  = ( Button ) findViewById( R.id.btn_Siete );
        cSiete .setOnClickListener( this );
        cOcho   = ( Button ) findViewById( R.id.btn_Ocho );
        cOcho .setOnClickListener( this );
        cNueve  = ( Button ) findViewById( R.id.btn_Nueve );
        cNueve .setOnClickListener( this );

        //-> Accedemos a los componentes del teclado de operaciones.
        //   Hacemos un TypeCast para un Button.
        cSuma =  ( Button ) findViewById( R .id .btn_Suma );
        cSuma .setOnClickListener( this );
        cResta = ( Button ) findViewById( R .id .btn_Resta );
        cResta .setOnClickListener( this );
        cMultiplica = ( Button ) findViewById( R .id .btn_Multiplicacion );
        cMultiplica .setOnClickListener( this );
        cDivide = ( Button ) findViewById( R .id .btn_Division );
        cDivide .setOnClickListener( this );
        cRaiz = ( Button ) findViewById( R .id .btn_RaizCuadrada );
        cRaiz .setOnClickListener( this );
        cCuadrado = ( Button ) findViewById( R .id .btn_Cuadrado );
        cCuadrado .setOnClickListener( this );

        //-> Accedemos al componente de botón punto del teclado .
        //   Hacemos un TypeCast para un Button.
        cPunto  = ( Button ) findViewById( R .id .btn_Decimal );
        cPunto .setOnClickListener( this );

        //-> Accedemos al componente de botón Igual del teclado.
        //   Hacemos un TyCast para un Button.
        cIgual = ( Button ) findViewById( R .id .btn_Igual );
        cIgual .setOnClickListener( this );

        //-> Accedemos a los componentes de botón para borrer en el teclado.
        //   Hacemos un TypeCast para un Button.
        cDEL = ( Button ) findViewById( R .id .btn_DEL );
        cDEL .setOnClickListener( this );
        cAC = ( Button ) findViewById( R .id .btn_AC );
        cAC .setOnClickListener( this );

    }

    //-> Inserta los número uno a uno en una cadena
    private void insertar( String valor ) {

        if( vDEL ) {
            vPunto = false;
            cPantalla .setText( "" );
            vDEL = false;
            vEntrada = "";
            vResultado = "";
            Toast .makeText( this, "DEL", Toast .LENGTH_SHORT) .show();
        }
        if( vAC ) {
            vPunto = false;
            cPantalla .setText( "" );
            vIniciaOperacion = false;
            vCapturaPrimerValor = false;
            vCapturaSegundoValor = false;
            vDEL = false;
            vAC = false;
            vIgual = false;
            vEntrada = "";
            vResultado = "";
            Toast .makeText( this, "AC", Toast .LENGTH_SHORT) .show();
        }

        vEntrada += valor;
        cPantalla .setText( vEntrada );     //: Refleja en Pantalla cada número insertado.

    }

    private void cuadrado() {

        double valor = Double .parseDouble( cPantalla .getText() .toString() );
        cPantalla .setText( String .valueOf( Math .pow( valor, 2 ) ) );

    }

    private void raiz() {

        double valor = Double .parseDouble( cPantalla .getText() .toString() );
        cPantalla .setText( String .valueOf( Math .sqrt( valor ) ) );

    }


    private double calcular( String accion, double valor1, double valor2 ) {

        double resultado = 0.0;

        vPunto = false;     //: Cambia el estado del uso del punto a falso.
        if( accion .equals( "suma" ) ) {
            resultado = valor1 + valor2;
        }
        if( accion .equals( "resta" ) ) {
            resultado = valor1 - valor2;
        }
        if( accion .equals( "multiplicacion" ) ) {
            resultado = valor1 * valor2;
        }
        if( accion .equals( "division" ) ) {
            resultado = valor1 / valor2;
        }

        return resultado;
    }

    private void borrar( String opcion ) {

        if( opcion .equals( "pantalla" ) ) {
            cPantalla .setText( "0" );
            cPantallaAuxiliar .setText( "Borra pantalla" );
            vTemporal = 0.0;
        }
        else if( opcion .equals( "total" ) ) {
            cPantalla .setText( "0" );
            cPantallaAuxiliar .setText( "Borra todo" );
            vTemporal = 0.0;
            vFinal = 0.0;
        }
        else if( opcion .equals( "calculo") ) {
            cPantallaAuxiliar .setText( "Borrado tras cálculo" );
        }

    }

    private void igual ( String operacion ) {

        if( operacion .equals( "" ) ) {
            cPantalla .setText( String .valueOf( vFinal ) );
        }
        else {
            if( !vIgual ) {
                vTemporal = Double.parseDouble( cPantalla .getText() .toString()) ;
                vOperacionTemporal = operacion;
                Toast.makeText( this, "Captura (2) " + String.valueOf(vTemporal), Toast.LENGTH_SHORT).show();
                vCapturaSegundoValor = true;
                vEntrada = "";
                vFinal = calcular( operacion, vFinal, vTemporal);
                cPantalla .setText( String.valueOf( vFinal ) );
                vResultado = String .valueOf( vFinal );
                vIgual = true;
            }
            else {
                cPantalla .setText( vResultado );     Toast .makeText( this, "HEY (2)!: ", Toast .LENGTH_SHORT ) .show();
            }
        }

    }

    private void operar( String operacion ) {

        //Toast .makeText( this, String .valueOf( vIniciaOperacion ), Toast .LENGTH_SHORT ) .show();

        //-> Valida si se da inicio a la primer cálculo.
        if( !vIniciaOperacion ) {
            vFinal = Double .parseDouble( cPantalla .getText() .toString() );
            vOperacionPrincipal = operacion;               Toast .makeText( this, "Captura (1) " + String .valueOf(vFinal), Toast .LENGTH_SHORT ) .show();
            vCapturaPrimerValor = true;
            vIgual = false;
            vIniciaOperacion = true;
            vEntrada = "";
        }
        else {
            vTemporal = Double .parseDouble( cPantalla .getText() .toString() );
            vOperacionTemporal = operacion;                 Toast .makeText( this, "Captura (2) " + String.valueOf( vTemporal ), Toast .LENGTH_SHORT ) .show();
            vCapturaSegundoValor = true;
            vEntrada = "";
            //if( !operacion .equals( "igual" ) ) {
                vFinal = calcular( vOperacionPrincipal, vFinal, vTemporal );
                cPantalla.setText( String.valueOf( vFinal ) );
                vOperacionTemporal = operacion;                 //Toast .makeText( this, "Resultado de la " + vOperacionPrincipal + ": " + String.valueOf( vFinal ), Toast .LENGTH_SHORT ) .show();
                vOperacionPrincipal = vOperacionTemporal;       //Toast .makeText( this, "Operacion Principal: " + vOperacionPrincipal , Toast .LENGTH_SHORT ) .show();
                vOperacionTemporal = "";
            //}
            //else {
                Toast .makeText( this, "vIgual!: " + vIgual, Toast .LENGTH_SHORT ) .show();

                //if( !vIgual ) {
                //    vFinal = calcular( vOperacionPrincipal, vFinal, vTemporal );
                //    cPantalla .setText( String .valueOf( vFinal ) );                Toast .makeText( this, "HEY (1)!: ", Toast .LENGTH_SHORT ) .show();
                //    vResultado = String .valueOf( vFinal );
                //    vOperacionPrincipal = "";                       //Toast .makeText( this, "Operacion Primaria: " + vOperacionPrincipal , Toast .LENGTH_SHORT ) .show();
                //    vOperacionTemporal = "";                        //Toast .makeText( this, "Operacion Temporal: " + vOperacionTemporal , Toast .LENGTH_SHORT ) .show();
                //    vIniciaOperacion = false;
                //    vIgual = true;                                  Toast .makeText( this, "vIgual!: " + vIgual, Toast .LENGTH_SHORT ) .show();
                //}
                //else {
                //    cPantalla .setText( vResultado );     Toast .makeText( this, "HEY (2)!: ", Toast .LENGTH_SHORT ) .show();
                //}
            //}
        }
    }
}
