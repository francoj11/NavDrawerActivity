package jaramillo.franco.navdraweractivity;

import android.os.Bundle;
import android.view.ViewGroup;

public class BlueActivity extends BaseNavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_blue, (ViewGroup) findViewById(R.id.containerLayout));
        setTitle(getString(R.string.blue_activity));
    }
}
