package groupm.goldnav;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.R;

public class SplashScreen extends AppCompatActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		ConstraintLayout splashScreen = (ConstraintLayout)findViewById(R.id.screen1);
		splashScreen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent nextPage = new Intent(v.getContext(), Selections.class);
				startActivityForResult(nextPage, 0);
			}
		});
	}
}