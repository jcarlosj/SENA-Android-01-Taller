package co.jce.sena.taller1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void imc( View v ) {
        Intent i = new Intent( this, IMCActivity.class );
        startActivity( i );
    }

    public void vacaciones( View v ) {
        Intent i = new Intent( this, VacacionesActivity.class );
        startActivity( i );
    }

    public void calculadora( View v ) {
        Intent i = new Intent( this, CalculadoraActivity.class );
        startActivity( i );
    }

    public void bienes_raices( View v ) {
        Intent i = new Intent( this, BienesRaicesActivity.class );
        startActivity( i );
    }

    public void get( View v ) {
        Intent i = new Intent( this, GetActivity.class );
        startActivity( i );
    }

    public void autos( View v ) {
        Intent i = new Intent( this, AutosActivity.class );
        startActivity( i );
    }

}
