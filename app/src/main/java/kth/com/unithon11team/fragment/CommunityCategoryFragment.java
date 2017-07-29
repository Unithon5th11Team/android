package kth.com.unithon11team.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindInt;
import kth.com.unithon11team.R;
import kth.com.unithon11team.adapter.CategoryCategoryAdapter;
import kth.com.unithon11team.api.MusicalService.Model.Musical;
import kth.com.unithon11team.api.MusicalService.MusicalServiceManager;
import kth.com.unithon11team.api.basemodel.BaseResponse;
import kth.com.unithon11team.api.basemodel.Result;
import kth.com.unithon11team.category.CategoryCategory;
import kth.com.unithon11team.category.CommunityCategory;
import kth.com.unithon11team.decoration.GridSpacingItemDecoration;
import kth.com.unithon11team.define.Args;
import kth.com.unithon11team.listener.RecyclerViewItemClickListener;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by kinamare on 2017-07-30.
 */

public class CommunityCategoryFragment extends RecyclerFragment implements RecyclerViewItemClickListener {

	public static final String TAG = "kth.com.nanamare.BasketCategoryFragment";

	private CategoryCategoryAdapter adapter;
	private CommunityCategory category = CommunityCategory.All;

	private List<Musical> musicalList;

	/**
	 * 인스턴스
	 *
	 * @param catetory
	 * @return
	 */
	public static CommunityCategoryFragment newInstance(CommunityCategory catetory) {
		Bundle args = new Bundle();
		args.putSerializable(Args.COMMUNITY, catetory);

		CommunityCategoryFragment fragment = new CommunityCategoryFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (view == null) return;

		Bundle args = getArguments();
		Serializable c = args.getSerializable(Args.COMMUNITY);

		/**
		 * 검증
		 */
		if (c != null && c instanceof CommunityCategory) {
			category = (CommunityCategory) c;


			recyclerView.setAdapter(adapter = new CategoryCategoryAdapter(new ArrayList<Musical>(), this));;
			ViewCompat.setNestedScrollingEnabled(recyclerView, false);

			requestMusicalToServer();

		}

		loadBundleData(savedInstanceState);


		onRefresh();
	}

	private void requestMusicalToServer() {
		MusicalServiceManager.getAllMusicalInfo()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Response<BaseResponse<Result>>>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onNext(Response<BaseResponse<Result>> baseResponseResponse) {
						musicalList = baseResponseResponse.body().mResult.allMusicalList;
						setAllMusicalList(musicalList);
					}
				});
	}

	private void setAllMusicalList(List<Musical> musicalList) {
		if(musicalList != null){
			adapter.clear();
			adapter.addAll(musicalList);
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onResume(){
		super.onResume();
		requestMusicalToServer();
	}

	private void loadBundleData(Bundle savedInstanceState) {
		Bundle bundle = (savedInstanceState != null) ? savedInstanceState : getArguments();

		if (bundle == null) {
			return;
		}

		Serializable category = bundle.getSerializable(Args.COMMUNITY);
		if (category != null) {
			this.category = (CommunityCategory) category;
		}
	}


	@Override
	protected int getLayoutId() {
		return R.layout.fragment_category;
	}

	@NonNull
	@Override
	protected RecyclerView.LayoutManager getLayoutManager() {
		return new LinearLayoutManager(getContext());
	}

	@Override
	public void onItemClick(View v, int position) {

	}
}
