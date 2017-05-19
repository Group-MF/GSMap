package groupm.goldnav;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.R;

public class Selections extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selections);
    }

    // Adds click listener to find room button
    public void openFindRoom(View v) {
        Intent i = new Intent(v.getContext(), FindRoom.class);
        startActivity(i);
    }

    // Adds click listener to show map button
    public void openShowMap(View v) {
        Intent i = new Intent(v.getContext(), ShowRHB.class);
        startActivity(i);
    }
}
