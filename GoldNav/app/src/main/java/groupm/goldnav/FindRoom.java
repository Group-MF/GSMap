package groupm.goldnav;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.R;

import Pathfinder.Options;

public class FindRoom extends AppCompatActivity {

    private Options toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_room);
        toggle = new Options();
    }

    public void toggleAccess(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        toggle.setAccess(checked);
    }

    public void toggleLargeRoom(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        toggle.setLargeRoom(checked);
    }

    public void toggleFireExits(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        toggle.setFireExits(checked);
    }

    public void onBackPressed() {
        Intent i = new Intent(this, Selections.class);
        startActivity(i);
    }
}
