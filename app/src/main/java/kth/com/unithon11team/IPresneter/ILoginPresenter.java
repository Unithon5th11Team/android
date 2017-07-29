package kth.com.unithon11team.IPresneter;

import rx.Observable;

/**
 * Created by kinamare on 2016-12-31.
 */

public interface ILoginPresenter {
	Observable<Void> signIn(String email, String pwd);
	Observable<Void> registerToken(String token, String email);

}
