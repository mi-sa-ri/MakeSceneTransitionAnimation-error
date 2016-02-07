package test.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_actitivty);
        Intent intent = new Intent(getApplicationContext(), A.class);
        startActivity(intent);
    }
}
