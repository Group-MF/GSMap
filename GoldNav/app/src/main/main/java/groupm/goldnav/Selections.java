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

        /*Button findRoom = (Button)findViewById(R.id.screen2);
        Button showMap = (Button)findViewById(R.id.screen2);

        // Adds click listener to find room button
        /*
        findRoom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextPage = new Intent(v.getContext(), FindRoom.class);
                startActivity(nextPage);
                finish();
            }
        });

        // Adds click listener to show map button
        showMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextPage = new Intent(v.getContext(), ShowRHB_F1.class);
                startActivity(nextPage);
                finish();
            }
        });*/
    }
}