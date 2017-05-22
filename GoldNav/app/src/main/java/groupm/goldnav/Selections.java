package groupm.goldnav;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.R;

public class Selections extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selections);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    // Adds click listener to find room button
    public void openFindRoom(View v) {
        startActivity(new Intent(this, FindRoom.class));
        finish();
    }

    // Adds click listener to show map button
    public void openShowMap(View v) {
        startActivity(new Intent(this, ShowMap.class));
        finish();
    }

    public void onBackPressed() {
    }
}