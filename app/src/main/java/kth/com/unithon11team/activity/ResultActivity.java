package kth.com.unithon11team.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import com.vocketlist.android.roboguice.log.Ln;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import kth.com.unithon11team.R;
import kth.com.unithon11team.api.MusicalService.Model.Musical;
import kth.com.unithon11team.api.MusicalService.MusicalServiceManager;
import kth.com.unithon11team.api.basemodel.BaseResponse;
import kth.com.unithon11team.api.basemodel.Result;
import kth.com.unithon11team.api.rekognation.model.RecognationImage;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.activity_result_circleIv)
    CircleImageView circleImageView;
    @BindView(R.id.activity_result_emotion_tv1)
    AppCompatTextView emotionTv1;
    @BindView(R.id.activity_result_emotion_tv2)
    AppCompatTextView emotionTv2;
    @BindView(R.id.activity_result_emotion_tv3)
    AppCompatTextView emotionTv3;
    @BindView(R.id.activity_result_done_iv)
    AppCompatImageView doneIv;


    public static final String REQUEST_IMAGE_FROM_CAMERA = "request_image_from_camera";
    public static final String TAG = "ResultActivity";
    public static final String REKOGNATION_IMAGE = "rekognation_image";

    private String mPriortyType = "happy";
    private Bitmap mBitmap = null;

    private List<Musical> musicalList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        if (intent != null && args != null) {
            //load to album mode
            decodePicData(args);
            decodeRecognationData(args);
        } else {
            emotionTv1.setText(getString(R.string.empty_emotion_title));
            emotionTv2.setText(getString(R.string.empty_emotion_again));
        }


    }

    private void decodeRecognationData(Bundle args) {
        if (args.getSerializable(REKOGNATION_IMAGE) != null) {
            List<RecognationImage> data = (List<RecognationImage>) args.getSerializable(REKOGNATION_IMAGE);
            setRecognationData(data);
        }
    }

    private void setRecognationData(List<RecognationImage> data) {
        if (data.size() != 0) {
            mPriortyType = data.get(0).type;
            emotionTv1.setText(String.format(getString(R.string.emotion_title), data.get(0).confidence, data.get(0).type));
            emotionTv2.setText(String.format(getString(R.string.emotion_title), data.get(1).confidence, data.get(1).type));
            emotionTv3.setText(String.format(getString(R.string.emotion_title), data.get(2).confidence, data.get(2).type));
        }
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

    @OnClick(R.id.activity_result_done_iv)
    void onDoneClick() {
        sendToServerEmotion();
    }

    private void sendToServerEmotion() {
        MusicalServiceManager.sendEmotionToServer(mPriortyType)
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
                        musicalList = baseResponseResponse.body().mResult.musicalList;
                    }
                });
    }

}
