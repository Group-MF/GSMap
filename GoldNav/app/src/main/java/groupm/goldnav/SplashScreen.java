package groupm.goldnav;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import com.R;

public class SplashScreen extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Adds click listener to whole screen
		ConstraintLayout splashScreen = (ConstraintLayout)findViewById(R.id.splash);
		splashScreen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(), Selections.class));
				finish();
			}
		});
	}
}