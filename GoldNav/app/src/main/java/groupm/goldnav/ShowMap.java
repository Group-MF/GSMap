package groupm.goldnav;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.R;

public class ShowMap extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    /*
    //onClick up button
    public void floorUp() {
        ImageView map = (ImageView)findViewById(R.id.RHB_Map);
        map.setImageResource(R.drawable.rhb_f2);
        /*
        if(map.getDrawable()) { // If 1st floor is displayed
            map.setImageResource(R.drawable.rhb_f2);      // Display 2nd floor
        } else if(map.getDrawable()) { // If 2nd floor is displayed
            map.setImageResource(R.drawable.rhb_f3);             // Display 3rd floor
        }
    }

    // onClick down button
    public void floorDown() {
        ImageView map = (ImageView) findViewById(R.id.RHB_Map);

        /*
        if (map.getDrawable().equals(R.drawable.rhb_f2)) {
            map.setImageResource(R.drawable.rhb_f1);
        } else if (map.getDrawable().equals(R.drawable.rhb_f3)) {
            map.setImageResource(R.drawable.rhb_f2);
        }

    }
    */
    public void onBackPressed() {
        startActivity(new Intent(this, Selections.class));
    }
}
