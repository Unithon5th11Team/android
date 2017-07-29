package kth.com.unithon11team.category;

import android.support.annotation.StringRes;

import kth.com.unithon11team.R;

/**
 * Created by kinamare on 2017-07-29.
 */

public enum CategoryCategory {
    All(R.string.category_all),
    HORROR(R.string.category_horror),
    HAPPY(R.string.category_happy),
    SAD(R.string.category_sad),
    ANGRY(R.string.category_angry),
    CONFUSED(R.string.category_confused),
    DISGUSTED(R.string.category_disgusted),
    SURPRISED(R.string.category_surprised),
    CALM(R.string.category_calm);

    private final int resId;

    CategoryCategory(@StringRes int id) {
        this.resId = id;
    }

    public int getResId() {
        return resId;
    }
}
