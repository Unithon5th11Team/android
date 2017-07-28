package kth.com.unithon11team.utils;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.google.firebase.FirebaseApp;

import java.io.File;

import kth.com.unithon11team.R;


/**
 * Created by kinamare on 2016-12-17.
 */

public class AppApplication extends Application {

	public static final String TAG = AppApplication.class.getSimpleName();

	private static AppApplication sInstance;

	public static synchronized AppApplication getInstance() {
		if (sInstance != null)
			return sInstance;
		return null;
	}

	public Context getContext() {
		return this.getApplicationContext();
	}


	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);

		MultiDex.install(this);
	}


	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		FirebaseApp.initializeApp(this);


	}

	public static String getPicPath(Context context){
		String dirPath = context.getFilesDir().getAbsolutePath() +"/" +
				getInstance().getContext().getString(R.string.app_name);
		Log.d(TAG,dirPath);
		File dir = new File(dirPath);
		if(!dir.exists())
			dir.mkdirs();
		return dirPath + "/";

	}





}
