package kth.com.unithon11team.api.MusicalService;

import android.icu.text.Collator;

import java.util.List;

import kth.com.unithon11team.api.MusicalService.Model.Musical;
import kth.com.unithon11team.api.basemodel.BaseResponse;
import kth.com.unithon11team.api.basemodel.Result;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by kinamare on 2017-07-29.
 */

public interface MusicalService {

	@GET("{KEY}/json/performanceMaster/{START_INDEX}/{END_INDEX}")
	Observable<Response<List<Musical>>> getMusicalList(
			@Path("KEY") String key, @Path("START_INDEX") String startIndex, @Path("END_INDEX") String endIndex);

	@FormUrlEncoded
	@POST("api/emotion")
	Observable<Response<BaseResponse<Result>>> getMusicals(@Field("emotion") String emotion);

	@GET("info")
	Observable<Response<BaseResponse<Result>>> getAllMusicalList();

	@GET("play/{index}")
	Observable<Response<BaseResponse<Result>>> getDettail(@Path("index") String pageNm);
}
