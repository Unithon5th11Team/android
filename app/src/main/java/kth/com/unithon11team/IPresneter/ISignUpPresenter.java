package kth.com.unithon11team.IPresneter;

import android.widget.EditText;

import rx.Observable;

/**
 * Created by kinamare on 2016-12-18.
 */

public interface ISignUpPresenter {
	void registerUser(String email, String pwd);

	void check_blank(EditText activity_signup_edt_id, EditText activity_signup_edt_pwd);

	Observable<Void> checkDuplicateEmail(String email);
}
