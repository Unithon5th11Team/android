package kth.com.unithon11team.GithubService;

import com.vocketlist.android.network.executor.Priority;
import com.vocketlist.android.network.service.ServiceHelper;

import java.util.List;

import kth.com.unithon11team.GithubService.Model.Contributor;
import kth.com.unithon11team.api.ServiceDefine;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by kinamare on 2017-04-26.
 */

public final class GithubServiceManager {

	private static final GithubService SERVICE = ServiceDefine.retrofit.create(GithubService.class);

	public static Observable<Response<List<Contributor>>> getContributorList(String owner, String repo){
		return SERVICE.repoContributors(owner, repo)
				.subscribeOn(ServiceHelper.getPriorityScheduler(Priority.MEDIUM));
	}
}
