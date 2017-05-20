package groupm.goldnav;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.R;

public class ShowMap extends AppCompatActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_map);
	}
}
/*
		// Creates button variables
        Button floorUp = (Button)findViewById(R.id.showRHB);
        Button floorDown = (Button)findViewById(R.id.showRHB);

        // Adds click listener to up button
        floorUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ImageView map = (ImageView)findViewById(R.id.showMapB);
                if(map.getDrawable().equals(R.drawable.rhb_f1)) { // If 1st floor is displayed
                    map.setImageResource(R.drawable.rhb_f2);      // Display 2nd floor
                } else if(map.getDrawable().equals(R.drawable.rhb_f2)) { // If 2nd floor is displayed
                    map.setImageResource(R.drawable.rhb_f3);             // Display 3rd floor
                }
            }
        });

        // Adds click listener to down button
        floorDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ImageView map = (ImageView)findViewById(R.id.RHB_Map);
                if(map.getDrawable().equals(R.drawable.rhb_f2)) {
                    map.setImageResource(R.drawable.rhb_f1);
                } else if(map.getDrawable().equals(R.drawable.rhb_f3)) {
                    map.setImageResource(R.drawable.rhb_f2);
                }
            }
        });
    }
}
*/