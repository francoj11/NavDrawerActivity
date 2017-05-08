package jaramillo.franco.navdraweractivity;

import android.os.Bundle;
import android.view.ViewGroup;

public class GreenActivity extends BaseNavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_green, (ViewGroup) findViewById(R.id.containerLayout));
        setTitle(getString(R.string.green_activity));
    }
}
