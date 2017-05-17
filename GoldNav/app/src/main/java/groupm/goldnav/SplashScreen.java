package groupm.goldnav;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.groupm.myapplication.R;

//import com.groupm.myapplication.R;

public class SplashScreen extends AppCompatActivity {

	public static final String firstMessage = "com";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
	}
}
