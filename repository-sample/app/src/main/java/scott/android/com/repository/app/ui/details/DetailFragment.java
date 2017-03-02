package scott.android.com.repository.app.ui.details;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.joda.time.DateTime;

import butterknife.BindView;
import scott.android.com.repository.R;
import scott.android.com.repository.base.view.BaseActivity;
import scott.android.com.repository.base.view.BaseFragment;
import scott.android.com.repository.entities.Theme;

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
public class DetailFragment extends BaseFragment {

    @BindView(R.id.iVFrgDetailBanner)
    ImageView iVFrgDetailBanner;
    @BindView(R.id.iVFrgDetailIcon)
    ImageView iVFrgDetailIcon;
    @BindView(R.id.tVFrgDetailTitle)
    TextView tVFrgDetailTitle;
    @BindView(R.id.tVFrgDetailDescription)
    TextView tVFrgDetailDescription;
    @BindView(R.id.tVFrgDetailCreated)
    TextView tVFrgDetailCreated;
    @BindView(R.id.tVFrgDetailLang)
    TextView tVFrgDetailLang;
    @BindView(R.id.tVFrgDetailSubscribers)
    TextView tVFrgDetailSubscribers;
    @BindView(R.id.tVFrgDetailSubmitText)
    TextView tVFrgDetailSubmitText;
    private Theme theme;

    public static DetailFragment newInstance(Theme theme) {
        Bundle args = new Bundle();
        args.putParcelable(Theme.THEME_ARG, theme);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_details, container, false);
    }

    @Override
    protected void initVars() {
        setHasOptionsMenu(true);
        setRetainInstance(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        theme = getArguments().getParcelable(Theme.THEME_ARG);
    }

    @Override
    protected void initViews() {
        tVFrgDetailTitle.setText(theme.getTitle());
        tVFrgDetailDescription.setText(theme.getDescription());
        tVFrgDetailCreated.setText(getString(R.string.frg_detail_create, new DateTime(theme.getCreated()).toString()));
        tVFrgDetailLang.setText(getString(R.string.frg_detail_lang, theme.getLang()));
        tVFrgDetailSubscribers.setText(getString(R.string.frg_detail_subs, theme.getSubscribers()));
        tVFrgDetailSubmitText.setText(theme.getSubmitText());
        Glide.with(iVFrgDetailBanner.getContext())
                .load(theme.getBannerImg())
                .error(ContextCompat.getDrawable(iVFrgDetailBanner.getContext(), R.drawable.bg_placeholder))
                .centerCrop()
                .into(iVFrgDetailBanner);

        Glide.with(iVFrgDetailIcon.getContext())
                .load(theme.getIconImg())
                .error(ContextCompat.getDrawable(iVFrgDetailBanner.getContext(), R.drawable.bg_placeholder))
                .centerCrop()
                .into(iVFrgDetailIcon);
    }

    @Override
    protected void initListeners() {

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                getActivity().onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
