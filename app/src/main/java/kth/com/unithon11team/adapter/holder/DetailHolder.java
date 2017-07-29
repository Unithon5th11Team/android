package kth.com.unithon11team.adapter.holder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import kth.com.unithon11team.R;
import kth.com.unithon11team.api.MusicalService.Model.Musical;

/**
 * Created by kinamare on 2017-07-30.
 */

public class DetailHolder extends BaseViewHolder<Musical> {


	Musical item;
	/**
	 * 생성자
	 *
	 * @param itemView
	 */
	public DetailHolder(View itemView) {
		super(itemView);
	}


	/**
	 * 데이터 바인드
	 *
	 * @param data
	 */
	@Override
	public void bind(Musical data) {
		this.item = data;
	}
}
