package kth.com.unithon11team.adapter.holder;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.vision.text.Line;

import butterknife.BindView;
import kth.com.unithon11team.R;
import kth.com.unithon11team.api.MusicalService.Model.Musical;
import kth.com.unithon11team.listener.RecyclerViewItemClickListener;

/**
 * Created by kinamare on 2017-07-29.
 */

public class CategoryHolder  extends BaseViewHolder<Musical> implements View.OnClickListener{

	@BindView(R.id.row_camera_category_iv)
	AppCompatImageView mCategoryIv;
	@BindView(R.id.row_category_title_tv)
	AppCompatTextView mTitletV;
	@BindView(R.id.row_category_local_tv)
	AppCompatTextView mLocalTv;
	@BindView(R.id.row_category_data_tv)
	AppCompatTextView mDataTv;
	@BindView(R.id.row_category_grade_tv)
	AppCompatTextView mGradeTv;
	@BindView(R.id.row_category_card_view)
	LinearLayout cardView;


	private RecyclerViewItemClickListener mListener;
	private Musical item;

	/**
	 * 생성자
	 *
	 * @param itemView
	 */
	public CategoryHolder(View itemView, RecyclerViewItemClickListener listener) {
		super(itemView);
		mListener = listener;
	}

	/**
	 * 데이터 바인드
	 * @param data
	 */

	@Override
	public void bind(Musical data){
		this.item = data;


		Glide.with(context)
				.load(item.mImageURL)
				.asGif()
				.crossFade()
				.placeholder(R.drawable.mock_musical1)
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)
				.into(mCategoryIv);

		mTitletV.setText(item.mTitle);

		mDataTv.setText(item.mDate);

		mLocalTv.setText(item.mLoc);

		mGradeTv.setText(item.mGrade);

		mCategoryIv.setOnClickListener(this);


	}

	@Override
	public void onClick(View view) {
		if(mListener != null) mListener.onItemClick(view, getAdapterPosition());
	}
}
