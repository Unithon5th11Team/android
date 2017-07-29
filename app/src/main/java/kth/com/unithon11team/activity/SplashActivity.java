package kth.com.unithon11team.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import kth.com.unithon11team.R;
import kth.com.unithon11team.utils.NetUtil;

public class SplashActivity extends AppCompatActivity {

	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {

				if (NetUtil.isNetworkAvailable(getApplicationContext())) {
					// network connect

					Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
					startActivity(intent);

				} else {
					// offline
					Toast.makeText(getApplicationContext(), getString(R.string.confirm_internet_connection), Toast.LENGTH_LONG).show();
					finish();


				}
			}
		}, 3000);
	}
}
