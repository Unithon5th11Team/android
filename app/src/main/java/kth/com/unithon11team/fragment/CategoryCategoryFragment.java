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
import kth.com.unithon11team.category.CategoryCategory;
import kth.com.unithon11team.define.Args;
import kth.com.unithon11team.listener.RecyclerViewItemClickListener;

/**
 * Created by kinamare on 2017-07-29.
 */

public class CategoryCategoryFragment extends RecyclerFragment implements RecyclerViewItemClickListener {

	public static final String TAG = "kth.com.nanamare.BasketCategoryFragment";

	@BindDimen(R.dimen.volunteer_category_grid_space) protected int space;


	private CategoryCategoryAdapter adapter;
	private CategoryCategory category = CategoryCategory.All;

	/**
	 * 인스턴스
	 *
	 * @param catetory
	 * @return
	 */
	public static CategoryCategoryFragment newInstance(CategoryCategory catetory) {
		Bundle args = new Bundle();
		args.putSerializable(Args.CATEGORY, catetory);

		CategoryCategoryFragment fragment = new CategoryCategoryFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (view == null) return;

		Bundle args = getArguments();
		Serializable c = args.getSerializable(Args.CATEGORY);

		/**
		 * 검증
		 */
		if (c != null && c instanceof CategoryCategory) {
			category = (CategoryCategory) c;

			List<Musical> musicalList = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				Musical musicalData = new Musical();
				musicalList.add(musicalData);
			}

			recyclerView.setAdapter(adapter = new CategoryCategoryAdapter(musicalList, this));
			ViewCompat.setNestedScrollingEnabled(recyclerView, false);

		}

		loadBundleData(savedInstanceState);


		onRefresh();
	}

	private void loadBundleData(Bundle savedInstanceState) {
		Bundle bundle = (savedInstanceState != null) ? savedInstanceState : getArguments();

		if (bundle == null) {
			return;
		}

		Serializable category = bundle.getSerializable(Args.CATEGORY);
		if (category != null) {
			this.category = (CategoryCategory) category;
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
