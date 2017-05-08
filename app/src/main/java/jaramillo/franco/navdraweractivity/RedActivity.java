package jaramillo.franco.navdraweractivity;

import android.os.Bundle;
import android.view.ViewGroup;

public class RedActivity extends BaseNavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_red, (ViewGroup) findViewById(R.id.containerLayout));
        setTitle(getString(R.string.red_activity));
    }
}
