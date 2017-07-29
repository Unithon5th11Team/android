package kth.com.unithon11team.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vocketlist.android.roboguice.log.Ln;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import kth.com.unithon11team.R;
import kth.com.unithon11team.api.rekognation.model.RecognationImage;

public class ResultActivity extends AppCompatActivity {

	@BindView(R.id.activity_result_circleIv) CircleImageView circleImageView;

	public static final String REQUEST_IMAGE_FROM_CAMERA = "request_image_from_camera";
	public static final String TAG = "ResultActivity";
	public static final String REKOGNATION_IMAGE = "rekognation_image";



	private Bitmap mBitmap = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		ButterKnife.bind(this);

		Intent intent = getIntent();
		Bundle args = intent.getExtras();
		if(intent != null && args != null ) {
			//load to album mode
			decodePicData(args);
			decodeRecognationData(args);
		}


	}

	private void decodeRecognationData(Bundle args) {
		RecognationImage data = (RecognationImage) args.getSerializable(REKOGNATION_IMAGE);

		setRecognationData(data);

	}

	private void setRecognationData(RecognationImage data) {

	}

	private void decodePicData(Bundle args) {
		Bitmap bitmap = null;
		String imgPath = args.getString(REQUEST_IMAGE_FROM_CAMERA);
		Uri imgUri = Uri.parse(imgPath);

		try {
			bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
		} catch (IOException exception) {
			Ln.d(TAG, exception);
		}

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] byteArray = stream.toByteArray();

		mBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


		circleImageView.setImageBitmap(mBitmap);

	}
}
