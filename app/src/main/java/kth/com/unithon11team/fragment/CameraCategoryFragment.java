package kth.com.unithon11team.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.OnClick;
import kth.com.unithon11team.R;
import kth.com.unithon11team.activity.ResultActivity;
import kth.com.unithon11team.api.basemodel.BaseResponse;
import kth.com.unithon11team.api.rekognation.RekognationServiceManager;
import kth.com.unithon11team.api.rekognation.model.RecognationImage;
import kth.com.unithon11team.category.CameraCategoty;
import kth.com.unithon11team.define.Args;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by kinamare on 2017-07-29.
 */

public class CameraCategoryFragment extends RecyclerFragment implements SurfaceHolder.Callback {

	public static final String TAG = "kth.com.nanamare.BasketCategoryFragment";
	public static final String REKOGNATION_IMAGE = "rekognation_image";
	public static final String REQUEST_IMAGE_FROM_CAMERA = "request_image_from_camera";

	@BindDimen(R.dimen.volunteer_category_grid_space)
	protected int space;

	@BindView(R.id.fragment_camera_custom_view)
	SurfaceView mCameraView;
	@BindView(R.id.fragment_camera_send_iv)
	AppCompatImageView mSendDoneIv;

	private CameraDevice camera;
	private SurfaceHolder mCameraHolder;
	private Camera mCamera;

	private CameraCategoty category = CameraCategoty.CAMERA_TITLE;

	private Bitmap mBitmap;

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

	@Override
	public void onResume(){
		super.onResume();
		init();
	}

	private void init() {

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

	@OnClick(R.id.fragment_camera_send_iv)
	void onSendCick() {
		mCamera.takePicture(null, null, mPicture);

	}

	private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

		@Override

		public void onPictureTaken(byte[] data, Camera camera) {

			// JPEG 이미지가 byte[] 형태로 들어옵니다.

			File pictureFile = getOutputMediaFile();

			// data[] 로 넘어온 데이터를 bitmap으로 변환
			Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);

			Matrix m = new Matrix();

			m.setRotate(90, (float) bmp.getWidth(), (float) bmp.getHeight());

			Bitmap rotateBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), m, false);

			mBitmap = rotateBitmap;

			bmp.recycle();

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			rotateBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byte[] byteArray = stream.toByteArray();

			if (pictureFile == null) {

				Toast.makeText(getContext(), "Error camera image saving", Toast.LENGTH_SHORT).show();

				return;

			}


			try {

				FileOutputStream fos = new FileOutputStream(pictureFile);

				fos.write(byteArray);

				fos.close();

				//Thread.sleep(500);

				mCamera.startPreview();

			} catch (FileNotFoundException e) {

				Log.d(TAG, "File not found: " + e.getMessage());

			} catch (IOException e) {

				Log.d(TAG, "Error accessing file: " + e.getMessage());

			}


			RekognationServiceManager.sendToImage(pictureFile.getAbsolutePath())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(new Subscriber<Response<BaseResponse<RecognationImage>>>() {
						@Override
						public void onCompleted() {

						}

						@Override
						public void onError(Throwable e) {
							Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
							Bundle args = new Bundle();
							args.putString(REQUEST_IMAGE_FROM_CAMERA, getImageUri(getContext(), mBitmap).toString());
							goToActivity(ResultActivity.class, args);
						}

						@Override
						public void onNext(Response<BaseResponse<RecognationImage>> baseResponseResponse) {
							RecognationImage recognationImage = baseResponseResponse.body().mResult;
							Bundle args = new Bundle();
							args.putSerializable(REKOGNATION_IMAGE, recognationImage);
							args.putString(REQUEST_IMAGE_FROM_CAMERA, getImageUri(getContext(), mBitmap).toString());
							goToActivity(ResultActivity.class, args);

						}
					});

		}

	};

	public Uri getImageUri(Context inContext, Bitmap inImage) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
		return Uri.parse(path);
	}


	/**
	 * 액티비티 호출
	 *
	 * @param cls
	 */
	private void goToActivity(Class<?> cls) {
		goToActivity(cls, null);
	}


	/**
	 * 액티비티 호출
	 *
	 * @param cls
	 * @param extras
	 */
	private void goToActivity(Class<?> cls, @Nullable Bundle extras) {
		Intent intent = new Intent(getContext(), cls);
		if (extras != null) intent.putExtras(extras);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}



	private static File getOutputMediaFile(){

		//SD 카드가 마운트 되어있는지 먼저 확인

		// Environment.getExternalStorageState() 로 마운트 상태 확인 가능합니다

		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MoodPlay");



		// 없는 경로라면 따로 생성

		if(!mediaStorageDir.exists()){

			if(! mediaStorageDir.mkdirs()){

				Log.d("MyCamera", "failed to create directory");

				return null;

			}

		}



		// 파일명을 적당히 생성, 여기선 시간으로 파일명 중복을 피한다

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		File mediaFile;



		mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timestamp + ".jpg");

		Log.i("MyCamera", "Saved at"+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));

		System.out.println(mediaFile.getPath());

		return mediaFile;

	}




}
