package kth.com.unithon11team.adapter.holder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;
import kth.com.unithon11team.R;
import kth.com.unithon11team.api.MusicalService.Model.Musical;
import kth.com.unithon11team.listener.RecyclerViewItemClickListener;

/**
 * Created by kinamare on 2017-07-29.
 */

public class CategoryHolder  extends BaseViewHolder<Musical> implements View.OnClickListener{


	private RecyclerViewItemClickListener mListener;
	private Musical data;

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
		this.data = data;

	}

	@Override
	public void onClick(View view) {
		if(mListener != null) mListener.onItemClick(view, getAdapterPosition());
	}
}
