package groupm.goldnav;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.R;

public class ShowMap extends AppCompatActivity {

    //ImageView rhb;
    TextView floorNum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        floorNum = (TextView) findViewById(R.id.floorNum);
    }

    public void floorUp(View view) {
        char[] floor = floorNum.getText().toString().toCharArray();
        if(floor[6] != '3') {
            int floorInt = Integer.valueOf(floor[6]);
            floorInt++;
            floor[6] = (char)floorInt;
            /*
            if(floor[6] == '2') {
                rhb.setImageResource(R.drawable.rhb_f3);
            } else if(floor[6] == '1') {
                rhb.setImageResource(R.drawable.rhb_f2);
            }*/
            floorNum.setText(new String(floor));
        }
    }

    public void floorDown(View view) {
        char[] floor = floorNum.getText().toString().toCharArray();
        if(floor[6] != '1') {
            int floorInt = Integer.valueOf(floor[6]);
            floorInt--;
            floor[6] = (char)floorInt;
            /*
            if(floor[6] == '3') {
                rhb.setImageResource(R.drawable.rhb_f2);
            } else if(floor[6] == '2') {
                rhb.setImageResource(R.drawable.rhb_f1);
            }
            */
            floorNum.setText(new String(floor));
        }
    }

    public void onBackPressed() {
        startActivity(new Intent(this, Selections.class));
    }
}
