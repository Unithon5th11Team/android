package kth.com.unithon11team.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vocketlist.android.roboguice.log.Ln;

import java.util.List;

import kth.com.unithon11team.R;
import kth.com.unithon11team.adapter.holder.CategoryHolder;
import kth.com.unithon11team.adapter.holder.DetailHolder;
import kth.com.unithon11team.api.MusicalService.Model.Musical;
import kth.com.unithon11team.listener.RecyclerViewItemClickListener;

/**
 * Created by kinamare on 2017-07-30.
 */

public class DetailAdapter extends BaseAdapter<Musical, DetailHolder> {

	/**
	 * 생성자
	 *
	 * @param data
	 */
	public DetailAdapter(List<Musical> data) {
		super(data);
	}

	@Override
	public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new DetailHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_detail, null));
	}

	@Override
	public void onBindViewHolder(DetailHolder holder, int position) {
		Ln.d("onBindViewHolder : position = " + position);
		holder.bind(getItem(position));
	}
}
