package groupm.goldsmithsmapapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Group M on 03/05/2017.
 */

public class MainMenu extends AppCompatActivity {
    @Override
    //This is the root page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
        //search
    public void searchWindow(View view) {
        Intent intent = new Intent(this, displaySearchActivity.class);
startActivity(intent);
    }


    //show map
    public void showMap(View view){
        Intent intent = new Intent(this, displaySearchActivity.class);
        startActivity(intent);
    }

}