package kth.com.unithon11team.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vocketlist.android.roboguice.log.Ln;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kth.com.unithon11team.R;
import kth.com.unithon11team.api.MusicalService.Model.Musical;
import kth.com.unithon11team.api.MusicalService.MusicalServiceManager;

public class RecommendActivity extends AppCompatActivity {

	public static final String TAG = "ResultActivity";
	public static final String TAG_CURRENT_EMOTION = "emotion";

	public static final String IMAGE_FROM_ALBUM = "image_from_album";

	@BindView(R.id.recommendRecycler)
	protected RecyclerView recommendRecycler;
	@BindView(R.id.activity_recommend_tv)
	protected AppCompatTextView mRcommendTv;
	private LinearLayoutManager linearLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommend);

		ButterKnife.bind(this);


		Intent intent = getIntent();
		Bundle args = intent.getExtras();
		List<Musical> musicals = (List<Musical>) args.getSerializable(TAG);
		String currentEmotion = args.getString(TAG_CURRENT_EMOTION);

		Toast.makeText(this, currentEmotion, Toast.LENGTH_SHORT).show();

		mRcommendTv.setText(String.format(getString(R.string.recommend_result), currentEmotion));

		linearLayoutManager = new LinearLayoutManager(this);
		recommendRecycler.setLayoutManager(linearLayoutManager);
		recommendRecycler.setAdapter(new RecommAdapter(musicals));
	}


	public class RecommendViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.item_recommend_iv)
		public AppCompatImageView mRecommendIv;
		@BindView(R.id.recommend_title)
		public AppCompatTextView title;
		@BindView(R.id.recommend_location)
		public AppCompatTextView location;
		@BindView(R.id.recommend_date)
		public AppCompatTextView date;
		@BindView(R.id.recommend_summary)
		public AppCompatTextView summary;

		public RecommendViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);

		}

	}

	public class RecommAdapter extends RecyclerView.Adapter<RecommendViewHolder> {

		List<Musical> mDataList;
		int position;

		public RecommAdapter(List<Musical> dataList) {
			this.mDataList = dataList;
		}

		@Override
		public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new RecommendViewHolder(LayoutInflater.from(parent.getContext())
					.inflate(R.layout.item_recommend, null));
		}

		@Override
		public void onBindViewHolder(RecommendViewHolder holder, int position) {
			holder.title.setText(mDataList.get(position).mTitle);
			holder.date.setText(mDataList.get(position).mDate);
			holder.location.setText(mDataList.get(position).mLoc);
			holder.summary.setText(mDataList.get(position).mContents1);

			holder.mRecommendIv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Uri uri = Uri.parse(mDataList.get(position).mSiteURL);
					Intent it = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(it);
				}
			});


			Glide.with(getApplicationContext())
					.load(mDataList.get(position).mImageURL)
					.crossFade()
					.diskCacheStrategy(DiskCacheStrategy.SOURCE)
					.into(holder.mRecommendIv);

		}


		@Override
		public int getItemCount() {
			return mDataList.size();
		}
	}

}
