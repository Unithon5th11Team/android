package kth.com.unithon11team.openapi.MusicalService;

import java.util.List;

import kth.com.unithon11team.openapi.MusicalService.Model.Musical;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by kinamare on 2017-07-29.
 */

public interface MusicalService {

	@GET("{KEY}/json/PerformanceMaster/{START_INDEX}/{END_INDEX}/")
	Observable<Response<List<Musical>>> getMusicalList(
			@Path("KEY") String key, @Path("START_INDEX") String startIndex, @Path("END_INDEX") String endIndex);

}
