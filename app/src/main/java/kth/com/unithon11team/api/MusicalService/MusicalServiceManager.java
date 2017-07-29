package kth.com.unithon11team.api.MusicalService;

import com.vocketlist.android.network.executor.Priority;
import com.vocketlist.android.network.service.ServiceHelper;

import java.util.ArrayList;
import java.util.List;

import kth.com.unithon11team.api.MusicalService.Model.Musical;
import kth.com.unithon11team.api.ServiceDefine;
import kth.com.unithon11team.api.basemodel.BaseResponse;
import kth.com.unithon11team.api.basemodel.Result;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by kinamare on 2017-07-29.
 */

public class MusicalServiceManager {

	private static final MusicalService SERVICE = ServiceDefine.openRetrofit.create(MusicalService.class);
	private static final MusicalService USERSERVICE = ServiceDefine.retrofit.create(MusicalService.class);
	private static final String MUSICAL_API_KEY = "775a454765706a7937366b774e6b68";

	public static List<Musical> dataList = new ArrayList<>();


	public static Observable<Response<List<Musical>>> getContributorList(String startIndex, String endIndex) {
		return SERVICE.getMusicalList(MUSICAL_API_KEY, startIndex, endIndex)
				.subscribeOn(ServiceHelper.getPriorityScheduler(Priority.MEDIUM));
	}

	public static Observable<Response<BaseResponse<Result>>> sendEmotionToServer(String emotion) {
		return USERSERVICE.getMusicals(emotion)
				.subscribeOn(ServiceHelper.getPriorityScheduler(Priority.MEDIUM));
	}

	public static Observable<Response<BaseResponse<Result>>> getAllMusicalInfo() {
		return USERSERVICE.getAllMusicalList()
				.subscribeOn(ServiceHelper.getPriorityScheduler(Priority.MEDIUM));
	}

	public static List<Musical> getMusicalList() {
		return dataList;
	}

}
