package kth.com.unithon11team.fragment;

import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import kth.com.unithon11team.R;
import kth.com.unithon11team.category.CameraCategoty;
import kth.com.unithon11team.define.Args;

/**
 * Created by kinamare on 2017-07-29.
 */

public class CameraCategoryFragment extends RecyclerFragment implements SurfaceHolder.Callback{

	public static final String TAG = "kth.com.nanamare.BasketCategoryFragment";

	@BindDimen(R.dimen.volunteer_category_grid_space) protected int space;

	@BindView(R.id.fragment_camera_custom_view) SurfaceView mCameraView;

	private CameraDevice camera;
	private SurfaceHolder mCameraHolder;
	private Camera mCamera;

	private CameraCategoty category = CameraCategoty.CAMERA_TITLE;

	/**
	 * 인스턴스
	 *
	 * @param catetory
	 * @return
	 */
	public static CameraCategoryFragment newInstance(CameraCategoty catetory) {
		Bundle args = new Bundle();
		args.putSerializable(Args.CAMERA, catetory);

		CameraCategoryFragment fragment = new CameraCategoryFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (view == null) {
			return;
		}

		loadBundleData(savedInstanceState);

		init();
		onRefresh();
	}

	private void init(){

		mCamera = Camera.open();
		mCamera.setDisplayOrientation(90);

		// surfaceview setting
		mCameraHolder = mCameraView.getHolder();
		mCameraHolder.addCallback(this);
		mCameraHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}



	private void loadBundleData(Bundle savedInstanceState) {
		Bundle bundle = (savedInstanceState != null) ? savedInstanceState : getArguments();

		if (bundle == null) {
			return;
		}

		Serializable category = bundle.getSerializable(Args.CAMERA);
		if (category != null) {
			this.category = (CameraCategoty) category;
		}
	}


	@Override
	protected int getLayoutId() {
		return R.layout.fragment_camera;
	}

	@NonNull
	@Override
	protected RecyclerView.LayoutManager getLayoutManager() {
		return new LinearLayoutManager(getContext());
	}


	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			if (mCamera == null) {
				mCamera.setPreviewDisplay(holder);
				mCamera.startPreview();
			}
		} catch (IOException e) {
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

		// View 가 존재하지 않을 때
		if (mCameraHolder.getSurface() == null) {
			return;
		}

		// 작업을 위해 잠시 멈춘다
		try {
			mCamera.stopPreview();
		} catch (Exception e) {
			// 에러가 나더라도 무시한다.
		}

		// 카메라 설정을 다시 한다.
		Camera.Parameters parameters = mCamera.getParameters();
		List<String> focusModes = parameters.getSupportedFocusModes();
		if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
			parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
		}
		mCamera.setParameters(parameters);

		// View 를 재생성한다.
		try {
			mCamera.setPreviewDisplay(mCameraHolder);
			mCamera.startPreview();
		} catch (Exception e) {
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mCamera != null) {
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
	}


}
