package kth.com.unithon11team.openapi.MusicalService;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import retrofit2.Response;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

/**
 * Created by kinamare on 2017-07-29.
 */

@RunWith(AndroidJUnit4.class)
public class Musical_InstrumentedTest {

	List<kth.com.unithon11team.openapi.MusicalService.Model.Musical> musicalList;

	@Before
	public void setup() {

		musicalList = null;

		// Start Current thread
		RxJavaPlugins.getInstance().reset();
		RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook() {
			@Override
			public Scheduler getComputationScheduler() {
				return Schedulers.immediate();
			}
		});

	}

	@Test
	public void 뮤지컬_리스트_가져오기_테스트() {
		MusicalServiceManager.getContributorList("1","2")
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Response<List<kth.com.unithon11team.openapi.MusicalService.Model.Musical>>>() {
					@Override
					public void onCompleted() {


					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onNext(Response<List<kth.com.unithon11team.openapi.MusicalService.Model.Musical>> listResponse) {
						musicalList = listResponse.body();
					}
				});

	}
}
