package jaramillo.franco.navdraweractivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by franco on 07/05/17.
 */

/**
 *  Clase abstracta base de las Activities que quieren usar un navigation drawer. Las activities
 *  que extienden esta clase pueden cargar su contenido en el layout llamado containerLayout. Ver
 *  BlueActivity como referencia.
 */
public abstract class BaseNavigationDrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;         // El DrawerLayout
    private ListView lvNavigationDrawer;        // La lista del panel lateral

    private String[] activitiesList;            // La lista de activities
    private String currentActivity;             // La activity actual, representada por su titulo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargamos el layout base. Contendrá el DrawerLayout, el panel lateral, y un contenedor
        // principal donde las activities que extienden esta activity pueden poner su contenido
        setContentView(R.layout.activity_base_navigation_drawer);

        // Inicializamos las variables
        currentActivity = "";
        activitiesList = getResources().getStringArray(R.array.activities);

        // Inicializamos el panel lateral, que en este caso es un simple ListView
        lvNavigationDrawer = (ListView) findViewById(R.id.lvNavigationDrawer);
        lvNavigationDrawer.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                                      activitiesList));

        // Seteamos el clickListener de la listView
        lvNavigationDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                onNavigationDrawerItemClick(position);
            }
        });

        // Seteamos el DrawerListener del DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.baseDrawerLayout);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {}

            @Override
            public void onDrawerOpened(View drawerView) {}

            @Override
            public void onDrawerClosed(View drawerView) {
                onNavigationDrawerClosed();
            }

            @Override
            public void onDrawerStateChanged(int newState) {}
        });
    }

    /**
     * Designa la proxima Activity a abrirse y cierra el navigationDrawer
     */
    private void onNavigationDrawerItemClick(int position) {
        currentActivity = activitiesList[position];
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    /**
     * Al cerrarse el navigationDrawer, se pregunta si hay que abrir alguna activity y se abre.
     * Si la Activity actual, es igual a la que se intenta abrir, entonces no se ignora el evento.
     */
    private void onNavigationDrawerClosed() {
        Intent intent = null;

        // Si la Activity actual es igual a la que se intenta abrir, ignoramos el evento
        if (currentActivity.compareTo((String) getTitle()) == 0) {
            currentActivity = "";
            return;
        }

        // Preparamos el intent de la Activity que se quiere abrir
        if (currentActivity.compareTo(getResources().getString(R.string.red_activity)) == 0) {
            intent = new Intent(BaseNavigationDrawerActivity.this,RedActivity.class);
        } else if (currentActivity.compareTo(getResources().getString(R.string.green_activity)) == 0) {
            intent = new Intent(BaseNavigationDrawerActivity.this,GreenActivity.class);
        } else if (currentActivity.compareTo(getResources().getString(R.string.blue_activity)) == 0) {
            intent = new Intent(BaseNavigationDrawerActivity.this,BlueActivity.class);
        }

        // Abrimos la Activity sin animación, para que parezca un simple cambio de fragment
        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }

        currentActivity = "";
    }
}
