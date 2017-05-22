package groupm.goldnav;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.widget.TextView;

import com.R;

import org.w3c.dom.Text;

public class Path extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView StartView = (TextView) findViewById(R.id.testStart);
        String start = getIntent().getExtras().getString("start");
        if(!start.equals(null)) {
            StartView.setText(start);
        }
    }

    public void onBackPressed() {
        startActivity(new Intent(this, FindRoom.class));
    }
}
