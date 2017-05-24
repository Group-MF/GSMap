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
import java.util.Arrays;

import com.R;

import Pathfinder.Options;
import Pathfinder.Searcher;

public class FindRoom extends AppCompatActivity {

    private Options toggle;
    private Searcher rooms;
    private AutoCompleteTextView location;
    private AutoCompleteTextView destination;
    String[] roomArr;
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
        roomArr = new String[0];
        try{
            rooms = new Searcher(getAssets());
            roomArr = rooms.getRooms();
        } catch(IOException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, roomArr);
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
        String start = location.getText().toString().trim();
        String end = destination.getText().toString().trim();
        if(Arrays.asList(roomArr).contains(start) && Arrays.asList(roomArr).contains(end)) {
            Intent pathInfo = new Intent(this, Path.class);
            pathInfo.putExtra("start", start);  // Saves variables to be used in next activity
            pathInfo.putExtra("end", end);      // Saves variables to be used in next activity
            pathInfo.putExtra("access", toggle.getAccess());
            pathInfo.putExtra("largeRooms", toggle.getLargeRoom());
            pathInfo.putExtra("fireExits", toggle.getFireExits());
            startActivity(pathInfo);
        }
    }

    public void onBackPressed() {
        startActivity(new Intent(this, Selections.class));
    }
}