package ch.hsr.mge.gadgeothek;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, new HomeFragment()).addToBackStack("home").commit();

        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {

                    }
                }
        );
    }

    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() <= 1){
            finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new SettingsFragment())
                        .addToBackStack("settings")
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.drawerHome:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new HomeFragment())
                        .addToBackStack("home")
                        .commit();
                break;
            case R.id.drawerLogin:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new LoginFragment())
                        .addToBackStack("login")
                        .commit();
                break;
            case R.id.drawerLoan:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new LoanFragment())
                        .addToBackStack("loan")
                        .commit();
                break;
            case R.id.drawerReservation:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new ReservationFragment())
                        .addToBackStack("reservation")
                        .commit();
                break;
        }
        item.setChecked(true);
        drawer.closeDrawers();
        return true;
    }


}