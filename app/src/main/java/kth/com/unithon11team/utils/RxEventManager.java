package kth.com.unithon11team.utils;

import rx.subjects.PublishSubject;

/**
 * description nanamare.tistory.com/8
 * Created by kinamare on 2017-05-12.
 * @function 모든 데이터를 원하는 상황에서 넘기고 받을수 있다.
 * ex) 프레그먼트간의 통신, 콜백 등 모든 상황에 적합하다
 */

public class RxEventManager {

	private static RxEventManager mInstance;

	private PublishSubject<Object> mData;

	private RxEventManager(){
		mData = PublishSubject.create();
	}

	public static RxEventManager getInstance(){
		if(mInstance == null) {
			mInstance = new RxEventManager();
		}
		return mInstance;
	}

	public void sendData(Object data){
		mData.onNext(data);
	}

	public rx.Observable<Object> getObjectObservable(){
		return mData;
	}

}
