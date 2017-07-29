package kth.com.unithon11team.category;

import android.support.annotation.StringRes;

import kth.com.unithon11team.R;

/**
 * Created by kinamare on 2017-07-29.
 */

public enum CameraCategoty {
	CAMERA_TITLE(R.string.category_camera);


	private final int resId;

	CameraCategoty(@StringRes int id) {
		this.resId = id;
	}

	public int getResId() {
		return resId;
	}
}
