package kth.com.unithon11team.GithubService;


import java.util.List;

import kth.com.unithon11team.GithubService.Model.Contributor;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by kinamare on 2017-04-26.
 */

public interface GithubService {

	@GET("/repos/{owner}/{repo}/contributors")
	Observable<Response<List<Contributor>>> repoContributors(
			@Path("owner") String owner,
			@Path("repo") String repo);

}
