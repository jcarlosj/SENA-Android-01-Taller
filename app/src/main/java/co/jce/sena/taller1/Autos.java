package co.jce.sena.taller1;

/*
 * Created by jce on 16/08/15.
 */
public class Autos {

    public int vIconAuto;
    public String vEscuderia,
                  vBase,
                  vJefeEquipo;

    //-> Constructores
    public Autos() {
        super();
    }

    public Autos( int icon, String escuderia, String base, String jefe ) {
        super();
        this .vIconAuto = icon;
        this .vEscuderia = escuderia;
        this .vBase = base;
        this .vJefeEquipo = jefe;
    }
}
