package kth.com.unithon11team.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import kth.com.unithon11team.R;

public class RecommendActivity extends AppCompatActivity {

    @BindView(R.id.recommendRecycler)
    private RecyclerView recommendRecycler;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        linearLayoutManager = new LinearLayoutManager(this);
        recommendRecycler.setLayoutManager(linearLayoutManager);
        recommendRecycler.setAdapter(new RecommAdapter());
    }


    public class RecommendViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recommend_title)
        AppCompatTextView title;
        @BindView(R.id.recommend_location)
        AppCompatTextView location;
        @BindView(R.id.recommend_date)
        AppCompatTextView date;
        @BindView(R.id.recommend_summary)
        AppCompatTextView summary;

        public RecommendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class RecommAdapter extends RecyclerView.Adapter<RecommendViewHolder> {

        @Override
        public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecommendViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_recommend, parent, false));
        }

        @Override
        public void onBindViewHolder(RecommendViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
