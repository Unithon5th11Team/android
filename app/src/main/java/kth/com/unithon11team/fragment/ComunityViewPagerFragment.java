package kth.com.unithon11team.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import kth.com.unithon11team.R;
import kth.com.unithon11team.activity.MainActivity;
import kth.com.unithon11team.adapter.TitleAdapter;
import kth.com.unithon11team.category.CategoryCategory;
import kth.com.unithon11team.category.CommunityCategory;

/**
 * Created by kinamare on 2017-07-30.
 */

public class ComunityViewPagerFragment  extends BaseFragment {

	@BindView(R.id.viewPager)
	ViewPager viewPager;

	private TitleAdapter mAdapter;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_community_viewpaper, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		MainActivity act = (MainActivity) getActivity();
		if (act == null) return;
		if (view == null) return;
		ButterKnife.bind(this, view);

		// 뷰페이저
		mAdapter = new TitleAdapter(getChildFragmentManager());
		for (CommunityCategory category : CommunityCategory.values()) {
			mAdapter.addFragment(CommunityCategoryFragment.newInstance(category), getString(category.getResId()));
		}
		viewPager.setAdapter(mAdapter);

		// 탭
		TabLayout tabLayout = ButterKnife.findById(act, R.id.tlCommunity);
		tabLayout.setupWithViewPager(viewPager);

		// 글쓰기
		AppCompatTextView btnFilter = ButterKnife.findById(getActivity(), R.id.btnWrite);
		btnFilter.setOnClickListener(v -> {});
	}


}
