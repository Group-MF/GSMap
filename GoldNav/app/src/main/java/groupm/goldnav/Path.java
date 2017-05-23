package groupm.goldnav;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.R;

import java.io.IOException;
import java.util.List;

import Pathfinder.NaviRHB;
import Pathfinder.Searcher;

public class Path extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ListView testL = (ListView) findViewById(R.id.list);
        NaviRHB pathfinder;
        Searcher rooms;
        String start = getIntent().getExtras().getString("start");
        String end = getIntent().getExtras().getString("end");
        String path[] = new String[0];
        pathfinder = new NaviRHB(start, end, getAssets());
        List<String> testList = pathfinder.getPath();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, testList);
        testL.setAdapter(adapter);
    }

    public void onBackPressed() {
        startActivity(new Intent(this, FindRoom.class));
    }
}
