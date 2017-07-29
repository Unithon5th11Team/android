package kth.com.unithon11team.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import kth.com.unithon11team.R;


/**
 * Created by kinamare on 2017-07-10.
 */

public class DepthActivity extends AppCompatActivity {


	public static final String TAG = "kth.com.nanamare.DepthActivity";

	private static final int PERMISSIONS_REQUEST_CODE = 9999;


	private boolean mIsRootActivity = false;
	private boolean mCloseFlag = false;


	private final Handler mCloseHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			mCloseFlag = false;
		}
	};



	private void checkThePemission() {
		if (Build.VERSION.SDK_INT > 22) {
			boolean hasPermission = (ContextCompat.checkSelfPermission(this,
					Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
			if (!hasPermission) {
				ActivityCompat.requestPermissions(this,
						new String[]{
								Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CODE);
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case PERMISSIONS_REQUEST_CODE: {
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					//reload my activity with permission granted or use the features what required the permission
				} else {
					Toast.makeText(this, getString(R.string.warning_content), Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	@Override
	protected void onCreate(Bundle s){
		super.onCreate(s);

		checkThePemission();

	}

	@Override
	public void onStart(){
		super.onStart();


	}

	@Override
	public void onBackPressed() {
		if (mIsRootActivity) {
			if (mCloseFlag == false) {
				showToast(getResources().getString(R.string.finish_application), Toast.LENGTH_SHORT);

				mCloseFlag = true;
				mCloseHandler.sendEmptyMessageDelayed(0, 3000);
			} else {
				finish();
				onRootFinish();
			}
		} else {
			super.onBackPressed();
		}
	}

	protected void onRootFinish() {}

	public void showToast(final String message, final int duration) {
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), message, duration).show();
			}
		});
	}



	protected void setRootActivity(boolean isRootActivity) {
		mIsRootActivity = isRootActivity;
	}


}
