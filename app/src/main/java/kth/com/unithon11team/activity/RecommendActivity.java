package kth.com.unithon11team.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import butterknife.ButterKnife;
import kth.com.unithon11team.R;
import kth.com.unithon11team.api.MusicalService.Model.Musical;

public class RecommendActivity extends AppCompatActivity {

	public static final String TAG = "ResultActivity";
	public static final String TAG_CURRENT_EMOTION = "emotion";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend);

		ButterKnife.bind(this);

		Intent intent = getIntent();
		if (intent != null) {
			List<Musical> musicalList = (List<Musical>) intent.getSerializableExtra(TAG);
			String currentEmotion = intent.getStringExtra(TAG_CURRENT_EMOTION);
		}


	}
}
