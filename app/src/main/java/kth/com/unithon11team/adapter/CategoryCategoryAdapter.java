package kth.com.unithon11team.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vocketlist.android.roboguice.log.Ln;

import java.util.List;

import kth.com.unithon11team.R;
import kth.com.unithon11team.adapter.holder.CategoryHolder;
import kth.com.unithon11team.api.MusicalService.Model.Musical;
import kth.com.unithon11team.listener.RecyclerViewItemClickListener;

/**
 * Created by kinamare on 2017-07-18.
 */

public class CategoryCategoryAdapter extends BaseAdapter<Musical, CategoryHolder> {


	private RecyclerViewItemClickListener mListener;
	/**
	 * 생성자
	 *
	 * @param data
	 */
	public CategoryCategoryAdapter(List<Musical> data, RecyclerViewItemClickListener listener) {
		super(data);
		mListener = listener;
	}

	@Override
	public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new CategoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_category, null), mListener);
	}

	@Override
	public void onBindViewHolder(CategoryHolder holder, int position) {
		Ln.d("onBindViewHolder : position = " + position);
		holder.bind(getItem(position));
	}

}
