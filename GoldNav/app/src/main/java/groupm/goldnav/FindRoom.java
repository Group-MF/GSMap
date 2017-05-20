package groupm.goldnav;

import android.content.Intent;
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
    public AutoCompleteTextView destinationACTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_room);
        /*
        destinationACTV = (AutoCompleteTextView)findViewById(R.id.enterDestination);
        toggle = new Options();
        try{
            rooms = new Searcher();
        } catch(IOException e) {
            System.err.print(e.toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, rooms.getRooms());
        destinationACTV.setAdapter(adapter);
        */
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
        Intent i = new Intent(this, Selections.class);
        startActivity(i);
    }
}