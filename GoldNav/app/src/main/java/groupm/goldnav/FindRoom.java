package groupm.goldnav;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;

import com.R;

import java.io.IOException;

import Pathfinder.Options;
import Pathfinder.Searcher;

public class FindRoom extends AppCompatActivity {

    private Options toggle;
    private Searcher rooms;
    private AutoCompleteTextView destinationACTV;
    private String start;
    private String end;
    AutoCompleteTextView location;
    AutoCompleteTextView destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            //toggle = savedInstanceState.getParcelable("toggle");
            start = savedInstanceState.getString("start");
        } else {
            //toggle = new Options();
        }
        setContentView(R.layout.activity_find_room);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toggle = new Options();
        location = (AutoCompleteTextView)findViewById(R.id.enterLocation);
        destination = (AutoCompleteTextView)findViewById(R.id.enterDestination);
        /*
        destinationACTV = (AutoCompleteTextView)findViewById(R.id.enterDestination);
        try{
            rooms = new Searcher();
        } catch(IOException e) {
            System.err.print(e.toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, rooms.getRooms());
        destinationACTV.setAdapter(adapter);
        */
    }

    public void onSaveInstanceState(Bundle state) {
        start = location.getText().toString();
        end = destination.getText().toString();
        state.putString("start", start);
        super.onSaveInstanceState(state);
    }

    public void onRestoreInstanceState(Bundle state) {
        state.get("start");
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

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

    public void onBackPressed() {
        startActivity(new Intent(this, Selections.class));
    }

    public void search() {
        //if(!start.equals(null) && !end.equals(null)) {
        startActivity(new Intent(this, Pathfinder.class));
        //} else {}
    }
}