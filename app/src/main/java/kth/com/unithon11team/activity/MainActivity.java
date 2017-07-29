package kth.com.unithon11team.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import kth.com.unithon11team.R;
import kth.com.unithon11team.fragment.CameraViewPagerFragment;
import kth.com.unithon11team.fragment.CategoryViewPagerFragment;
import kth.com.unithon11team.utils.DepthActivity;

public class MainActivity extends DepthActivity implements NavigationView.OnNavigationItemSelectedListener,
		BottomNavigationView.OnNavigationItemSelectedListener,
		OnTabSelectListener {


	@BindView(R.id.toolbar) protected Toolbar mToolbar;
	@BindView(R.id.bottomBar) protected BottomBar bottomBar;
	@BindView(R.id.drawer_layout) protected DrawerLayout mDrawer;
	//	@BindView(R.id.navigationView) protected NavigationView mNavigationView;
	@BindView(R.id.llCommunityTab) LinearLayout llCommunityTab;
	@BindView(R.id.llCameraTab) LinearLayout llCameraTab;
	@BindView(R.id.llCategoryTab) LinearLayout llCategoryTab;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		initToolbar();
		initBottomNavigation();

	}

	private void initBottomNavigation() {
		bottomBar.setOnTabSelectListener(this, true);
		bottomBar.setDefaultTab(R.id.action_category);
		goToFragment(CategoryViewPagerFragment.class);
	}


	private void initToolbar() {
		// 헤더 CI 적용
		setSupportActionBar(mToolbar);

		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(
				getLayoutInflater().inflate(R.layout.appbar_title, null),
				new ActionBar.LayoutParams(
						ActionBar.LayoutParams.WRAP_CONTENT,
						ActionBar.LayoutParams.WRAP_CONTENT,
						Gravity.CENTER
				)
		);
	}



	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		return false;
	}

	@Override
	public void onTabSelected(@IdRes int tabId) {
		// TODO 전달할 값이 있으면 extras 파라미터에 담아서...
		switch (tabId) {
			// 검색 카메라
			case R.id.action_camera :
				llCommunityTab.setVisibility(View.INVISIBLE);
				llCategoryTab.setVisibility(View.INVISIBLE);
				llCameraTab.setVisibility(View.VISIBLE);
				goToFragment(CameraViewPagerFragment.class);
				break;
			// 커뮤니티
			case R.id.action_community :
				llCommunityTab.setVisibility(View.VISIBLE);
				llCategoryTab.setVisibility(View.INVISIBLE);
				llCameraTab.setVisibility(View.INVISIBLE);
//				goToFragment(BasketFragment.class);
				break;
			//카테고리
			case R.id.action_category :
				llCommunityTab.setVisibility(View.INVISIBLE);
				llCategoryTab.setVisibility(View.VISIBLE);
				llCameraTab.setVisibility(View.INVISIBLE);
				goToFragment(CategoryViewPagerFragment.class);
				break;
		}
	}


	/**
	 * 플래그먼트
	 *
	 * @param cls
	 */
	private void goToFragment(Class<?> cls) {
		goToFragment(cls, null);
	}

	/**
	 * 플래그먼트
	 *
	 * @param cls
	 * @param args
	 */
	private void goToFragment(Class<?> cls, @Nullable Bundle args) {
		try {
			Fragment fragment = (Fragment) cls.newInstance();
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}
