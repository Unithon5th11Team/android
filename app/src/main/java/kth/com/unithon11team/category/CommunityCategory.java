package kth.com.unithon11team.category;

import android.support.annotation.StringRes;

import kth.com.unithon11team.R;

/**
 * Created by kinamare on 2017-07-30.
 */

public enum CommunityCategory {
	All(R.string.community_all_title),
	ME(R.string.community_my_writing_title);



	private final int resId;

	CommunityCategory(@StringRes int id) {
		this.resId = id;
	}

	public int getResId() {
		return resId;
	}
}
