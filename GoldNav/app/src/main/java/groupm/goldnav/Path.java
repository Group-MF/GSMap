package groupm.goldnav;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.R;

import Pathfinder.NaviRHB;
import Pathfinder.Options;
import Pathfinder.drawPathF1;

public class Path extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ListView testL = (ListView) findViewById(R.id.list);
        TextView location = (TextView) findViewById(R.id.location);
        TextView destination = (TextView) findViewById(R.id.destination);
        Options toggle = new Options();

        String start = getIntent().getExtras().getString("start");
        String end = getIntent().getExtras().getString("end");
        toggle.setAccess(getIntent().getExtras().getBoolean("access"));
        toggle.setLargeRoom(getIntent().getExtras().getBoolean("largeRooms"));
        toggle.setFireExits(getIntent().getExtras().getBoolean("fireExits"));

        NaviRHB pathfinder = new NaviRHB(start, end, getAssets(), toggle);
        //getResources().getIdentifier("@drawable/rhb_f1", "drawable", this.getPackageName());
        drawPathF1 drawPath = new drawPathF1();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, pathfinder.getPath());
        testL.setAdapter(adapter);
        location.setText(start);
        destination.setText(end);
    }

    public void onBackPressed() {
        startActivity(new Intent(this, FindRoom.class));
    }
}