package kth.com.unithon11team.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kth.com.unithon11team.R;
import kth.com.unithon11team.api.MusicalService.Model.Musical;
import kth.com.unithon11team.api.MusicalService.MusicalServiceManager;

public class DetailActivity extends AppCompatActivity {

	public static final String TAG = "kth.com.nanamare.CategoryCategoryFragment";


	@BindView(R.id.start_date_tv) AppCompatTextView mDateTv;
	@BindView(R.id.row_category_detail_title) AppCompatTextView mDetailTitle;
	@BindView(R.id.row_category_genre) AppCompatTextView mGenre;
	@BindView(R.id.active_local_tv) AppCompatTextView mLocalTv;
	@BindView(R.id.man_tv) AppCompatTextView mManTv;
	@BindView(R.id.limit_old_tv) AppCompatTextView mLimitOldTv;
	@BindView(R.id.poster_address_tv) AppCompatTextView mAddressTv;
	@BindView(R.id.host_site_tv) AppCompatTextView mHostSiteTv;
	@BindView(R.id.ticketing_address) AppCompatTextView mTicketingAddress;
	@BindView(R.id.time_tv) AppCompatTextView mTimeTv;
	@BindView(R.id.read_detail_content) AppCompatTextView mContent;
	@BindView(R.id.poster_image_tv) AppCompatImageView mImageView;

	int pageNm;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		ButterKnife.bind(this);

		Bundle args = getIntent().getExtras();
		pageNm = args.getInt(TAG);

		setData();


	}

	private void setData() {
		List<Musical> itemList =  MusicalServiceManager.getMusicalList();
		Musical  musical = itemList.get(pageNm);

		mAddressTv.setText(musical.mLoc);
		mContent.setText(musical.mContents1 + "\n" + musical.mContents2);
		mDateTv.setText(musical.mDate);
		mDetailTitle.setText(musical.mTitle);
		mGenre.setText(musical.mGenre);
		mHostSiteTv.setText(musical.mSiteURL);
		mLimitOldTv.setText(musical.mGrade);
		mManTv.setText(musical.mMembers);
		mTicketingAddress.setText(musical.mTicketingURL);
		mLocalTv.setText(musical.mLoc);
		mTimeTv.setText(musical.mTime);

		Glide.with(this)
				.load(musical.mImageURL)
				.asGif()
				.crossFade()
				.placeholder(R.drawable.mock_musical2)
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)
				.into(mImageView);

	}
}
