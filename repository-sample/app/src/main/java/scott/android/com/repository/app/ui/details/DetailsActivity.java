package scott.android.com.repository.app.ui.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import scott.android.com.repository.R;
import scott.android.com.repository.base.view.BaseActivity;
import scott.android.com.repository.entities.Theme;
/**
 * @author pedroscott. scott7462@gmail.com
 * @version 1/19/17.
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
public class DetailsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private static final String CONTENT_FRAGMENT = "content";

    public static void newInstance(Activity activity, Theme theme) {
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putExtra(Theme.THEME_ARG, theme);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar(toolbar);
        setSupportActionBar(toolbar);
        savedFragmentState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, CONTENT_FRAGMENT, getSupportFragmentManager().findFragmentById(R.id.container));
    }


    private void savedFragmentState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            navigateMainContent(getSupportFragmentManager().getFragment(
                    savedInstanceState, CONTENT_FRAGMENT), getString(R.string.app_name));
        } else {
            navigateMainContent(DetailFragment.newInstance((Theme) getIntent().getParcelableExtra(Theme.THEME_ARG)),
                    ((Theme) getIntent().getParcelableExtra(Theme.THEME_ARG)).getDisplayName());
        }
    }


}
