package kth.com.unithon11team.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.vocketlist.android.roboguice.log.Ln;


/**
 * 플래그먼트 : 기본
 *
 * @author Jungho Song (kakao) & Hyunsung Sin(inu.stu)
 * @since 2017. 2. 14.
 */
public class BaseFragment extends Fragment implements SearchView.OnQueryTextListener,
View.OnClickListener {

    private MenuItem mi;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Ln.d("onQueryTextSubmit : " + query + ", class : " + this.getClass().getSimpleName());
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Ln.d("onQueryTextChange : " + newText + ", class : " + this.getClass().getSimpleName());
        return true;
    }

    public void closeSearchView() {
        //before request server, if you need to add code Todo

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case android.support.v7.appcompat.R.id.search_close_btn : {
                mi.collapseActionView();
                closeSearchView();
                break;
            }
        }
    }
}
