package kth.com.unithon11team;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

public class PlayActivity extends AppCompatActivity {

    private AppCompatImageView imgProfile;
    private AppCompatTextView play_name, play_date, play;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        initView();
        initToolbar();

    }


    private void initToolbar() {
        // 헤더 CI 적용
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(
                getLayoutInflater().inflate(R.layout.appbar_title, null),
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        Gravity.CENTER
                )
        );
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgProfile = (AppCompatImageView)findViewById(R.id.imgProfile);


    }

}
