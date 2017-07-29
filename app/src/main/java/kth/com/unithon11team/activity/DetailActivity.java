package kth.com.unithon11team.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kth.com.unithon11team.R;

public class DetailActivity extends AppCompatActivity {

	@BindView(R.id.activity_detail_super_recyclerview)
	SuperRecyclerView superRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		ButterKnife.bind(this);

	}
}
