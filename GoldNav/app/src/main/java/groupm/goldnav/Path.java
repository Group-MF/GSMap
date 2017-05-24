package groupm.goldnav;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.view.View;import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.R;

import Pathfinder.NaviRHB;
import Pathfinder.Options;
import Pathfinder.drawPathF1;

public class Path extends AppCompatActivity {

    //ImageView rhb;
    TextView floorNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ListView testL = (ListView) findViewById(R.id.list);
        TextView location = (TextView) findViewById(R.id.location);
        TextView destination = (TextView) findViewById(R.id.destination);
        floorNum = (TextView) findViewById(R.id.floorNum);
        Options toggle = new Options();

        String start = getIntent().getExtras().getString("start");
        String end = getIntent().getExtras().getString("end");
        toggle.setAccess(getIntent().getExtras().getBoolean("access"));
        toggle.setLargeRoom(getIntent().getExtras().getBoolean("largeRooms"));
        toggle.setFireExits(getIntent().getExtras().getBoolean("fireExits"));

        NaviRHB pathfinder = new NaviRHB(start, end, getAssets(), toggle);
        floorNum.setText("Floor " + pathfinder.getPath().get(0).charAt(1));
        //String[] maps = NaviRHB.PathInfo.getMaps();
        /*for(String floor: maps) {
            if(floor.equals("F1")){
                drawPathF1 drawPath = new drawPathF1();
            } else if(floor.equals("F2")){
                //drawPathF2 drawPath = new drawPathF2();
            } else {
                //drawPathF2 drawPath = new drawPathF2();
            }
        }*/
        //rhb = (ImageView)findViewById(R.id.rhb);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, pathfinder.getPath());
        testL.setAdapter(adapter);
        location.setText(start);
        destination.setText(end);
    }

    public void floorUp(View view) {
        char[] floor = floorNum.getText().toString().toCharArray();
        if(floor[6] != '3') {
            //char[] rhbChar = "R.drawable.rhb_f _path".toCharArray();
            int floorInt = Integer.valueOf(floor[6]);
            floorInt++;
            floor[6] = (char)floorInt;
            //rhbChar[16] = (char)floorInt;
            //String floorImage = new String(rhbChar);
            /*
            rhb.setImageDrawable(getResources().getDrawable(floorImage));
             */
            floorNum.setText(new String(floor));
        }
    }

    public void floorDown(View view) {
        char[] floor = floorNum.getText().toString().toCharArray();
        if(floor[6] != '1') {
            //char[] rhbChar = "R.drawable.rhb_f _path".toCharArray();
            int floorInt = Integer.valueOf(floor[6]);
            floorInt--;
            floor[6] = (char)floorInt;
            //rhbChar[16] = (char)floorInt;
            //String floorImage = new String(rhbChar);
            /*
            rhb.setImageDrawable(getResources().getDrawable(floorImage));
             */
            floorNum.setText(new String(floor));
        }
    }

    public void onBackPressed(View view) {
        startActivity(new Intent(this, FindRoom.class));
    }
}