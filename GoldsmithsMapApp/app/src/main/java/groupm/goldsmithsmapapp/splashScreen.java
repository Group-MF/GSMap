package groupm.goldsmithsmapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "groupm.goldsmiths.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    /*
    "Touch to Enter"
    Invisible button (or interactive image) is used to trigger the menu activity

     */
    public void enterMenu(View view) {
        Intent toMenu = new Intent(this, MainMenu.class);
//data entry mode
// map view mode with panning and line drawings
    }
}