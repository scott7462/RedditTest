package scott.android.com.reddittest.app.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import scott.android.com.reddittest.R;
import scott.android.com.reddittest.app.ui.details.DetailsActivity;
import scott.android.com.reddittest.app.ui.main.presenter.MainPresenter;
import scott.android.com.reddittest.app.ui.main.presenter.MainPresenterListener;
import scott.android.com.reddittest.base.view.BaseFragmentMVPList;
import scott.android.com.reddittest.base.view.BaseSimpleAdapter;
import scott.android.com.reddittest.entities.Theme;
import scott.android.com.reddittest.utils.MarginDecoration;

/**
 * @author pedroscott. scott7462@gmail.com
 * @version 1/16/17.
 *          <p>
 *          Copyright (C) 2015 The Android Open Source Project
 *          <p/>
 *          Licensed under the Apache License, Version 2.0 (the "License");
 *          you may not use this file except in compliance with the License.
 *          You may obtain a copy of the License at
 *          <p/>
 * @see <a href = "http://www.aprenderaprogramar.com" /> http://www.apache.org/licenses/LICENSE-2.0 </a>
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class MainFragment extends BaseFragmentMVPList<MainPresenter, Theme, ThemeAdapter> implements MainPresenterListener {

    @BindView(R.id.rVFrgHome)
    RecyclerView rVFrgHome;
    @BindView(R.id.sRFrgHome)
    SwipeRefreshLayout sRFrgHome;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_main, container, false);
    }

    @Override
    protected void initVars() {
        setHasOptionsMenu(true);
        setRetainInstance(true);
        setAdapter(new ThemeAdapter());
        setPresenter(new MainPresenter());
        getPresenter().doGetTheme();
        getAdapter().showLoadingState(true);
        getAdapter().addClickListener(new BaseSimpleAdapter.onItemClickListener<Theme>() {
            @Override
            public void onItemViewsClick(Theme item, int position) {
                DetailsActivity.newInstance(getActivity(), item);
            }
        });
    }

    @Override
    protected void initViews() {
        rVFrgHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        rVFrgHome.addItemDecoration(new MarginDecoration());
        setRecyclerView(rVFrgHome);
        setSwipeRefresh(sRFrgHome);
    }

    @Override
    protected void initListeners() {

    }


    @Override
    protected void onDoLoadItems() {
        getPresenter().doGetTheme();
    }

    @Override
    protected void onDoLoadMoreItems() {

    }

    @Override
    protected void onItemsLoaded(List<Theme> themes) {
        getAdapter().cleanItemsAndUpdate(themes);
    }

    @Override
    protected void onMoreItemsLoaded(List<Theme> newItems) {

    }
}
