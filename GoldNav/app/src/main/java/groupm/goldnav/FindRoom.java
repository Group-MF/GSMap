package groupm.goldnav;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;

import java.io.*;

import com.R;

import Pathfinder.Options;
import Pathfinder.Searcher;

public class FindRoom extends AppCompatActivity {

    private Options toggle;
    private Searcher rooms;
    private AutoCompleteTextView location;
    private AutoCompleteTextView destination;
    private String start;
    private String end;
    private static final int AUTO_INFO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_room);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toggle = new Options();
        location = (AutoCompleteTextView)findViewById(R.id.enterLocation);
        destination = (AutoCompleteTextView)findViewById(R.id.enterDestination);
        /*
        if(savedInstanceState != null) {
            start = savedInstanceState.getString("start");
            end = savedInstanceState.getString("end");
        }
        */
        String[] tempArr = new String[0];
        try{
            rooms = new Searcher(getAssets());
            tempArr = rooms.getRooms();
        } catch(IOException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, tempArr);
        location.setThreshold(1);
        location.setAdapter(adapter);
        destination.setThreshold(1);
        destination.setAdapter(adapter);
    }
    /*
    public void onSaveInstanceState(Bundle state) {
        start = location.getText().toString();
        end = destination.getText().toString();
        state.putString("start", start);
        state.putString("end", end);
        super.onSaveInstanceState(state);
    }

    private void save() {
        SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
        editor.putString("start", start);
        editor.commit();
    }

    public void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        start = state.getString("start");
        end = state.getString("end");
    }*/

    public void toggleAccess(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        toggle.setAccess(checked);
    }

    public void toggleLargeRoom(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        toggle.setLargeRoom(checked);
    }

    public void toggleFireExits(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        toggle.setFireExits(checked);
    }

    public void search(View view) {
        start = location.getText().toString();
        end = destination.getText().toString();
        Intent temp = new Intent(this, Path.class);
        temp.putExtra("start", start);  // Saves variables to be used in next activity
        temp.putExtra("end", end);      // Saves variables to be used in next activity
        startActivity(temp);
    }

    public void onBackPressed() {
        startActivity(new Intent(this, Selections.class));
    }
}