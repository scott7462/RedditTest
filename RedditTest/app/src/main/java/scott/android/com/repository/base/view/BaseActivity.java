package scott.android.com.repository.base.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import scott.android.com.repository.R;
import scott.android.com.repository.bus.event.EventAlterDialog;
import scott.android.com.repository.bus.event.EventProgressDialog;
import scott.android.com.repository.bus.event.EventSnackBar;
import scott.android.com.repository.bus.util.AlterDialogFragment;
import scott.android.com.repository.bus.util.SnackBarUtils;

/**
 * @author pedroscott. scott7462@gmail.com
 * @version 12/3/16.
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
public class BaseActivity extends AppCompatActivity {

    private final ArrayList<String> titleStack = new ArrayList<>();
    private Toolbar toolbar;
    private ProgressDialog progress;
    private CompositeSubscription subscription;
    private Unbinder unbinder;

    public CompositeSubscription getSubscription() {
        return subscription;
    }

    public void addSubscription(Subscription subscription) {
        getSubscription().add(subscription);
    }

    /**
     * Get the toolbar in the baseActivity instance.
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscription = new CompositeSubscription();
        EventBus.getDefault().register(this);
        progress = new ProgressDialog(this);
    }


    @CallSuper
    protected void onDestroy() {
        if(this.subscription != null) {
            this.subscription.unsubscribe();
        }

        if(this.unbinder != null) {
            this.unbinder.unbind();
        }

        super.onDestroy();
    }

    /**
     * Set the toolbar to the baseActivity instance.
     */
    protected void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    /**
     * Method to navigate using  FragmentTransaction and FragmentManager.
     */
    private static void navigateTo(FragmentManager manager, Fragment newFragment, int containerId, boolean options) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(containerId, newFragment, newFragment.getClass().getSimpleName());
        if (options) {
            ft.addToBackStack(newFragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    @CallSuper
    protected void onResume() {
        super.onResume();
        this.updateActionBarTitle();
    }

    @CallSuper
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    /**
     * clean Fragment Stack in the FragmentManager.
     */
    private static void cleanFragmentStack(FragmentManager fm) {
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Update the toolbar title.
     */
    private void updateActionBarTitle() {
        if (getToolbar() != null) {
            getToolbar().setTitle(titleStack.get(titleStack.size() - 1));
        }
    }

    /**
     * Navigate in the R.id.container with the SupportFragmentManager.
     */
    public void navigateMainContent(Fragment frg, String title) {
        cleanFragmentStack(getSupportFragmentManager());
        navigateTo(getSupportFragmentManager(), frg, R.id.container, false);
        titleStack.clear();
        titleStack.add(title);
        updateActionBarTitle();
    }

    /**
     * Navigate in the R.id.container with the SupportFragmentManager.
     */
    public void navigateLowContent(Fragment frg, String title) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        navigateTo(getSupportFragmentManager(), frg, R.id.container, true);
        titleStack.add(title);
        updateActionBarTitle();
    }

    /**
     * Navigate in the any FrameLayout container with the SupportFragmentManager.
     */
    public void navigateDetailContent(Fragment frg, String title, int container) {
        navigateTo(getSupportFragmentManager(), frg, container, false);
        titleStack.add(title);
        updateActionBarTitle();
    }

    protected Fragment getCurrentFrg() {
        return getSupportFragmentManager().findFragmentById(R.id.container);
    }

    @CallSuper
    public void onBackPressed() {
        this.clearKeyboardFromScreen();
        if(this.titleStack.size() > 0) {
            this.titleStack.remove(this.titleStack.size() - 1);
            if(this.titleStack.size() > 0) {
                this.updateActionBarTitle();
            }
        }
        super.onBackPressed();
    }

    public void clearKeyboardFromScreen() {
        if (this.getWindow().getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.getWindow().getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showProgressDialog(String message) {
        if (progress != null) {
            progress.setMessage(message);
            progress.show();
        }
    }

    public void dissmisProgressDialog() {
        if (progress != null) {
            progress.dismiss();
        }
    }

    @Subscribe
    public void showSnackBar(EventSnackBar eventSnackBar) {
        eventSnackBar.setViewParent(findViewById(android.R.id.content));
        SnackBarUtils.makeSnackBar(eventSnackBar);
    }

    @Subscribe
    public void showAlterDialog(EventAlterDialog event) {
        AlterDialogFragment newFragment = new AlterDialogFragment();
        newFragment.setEventAlterDialog(event);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Subscribe
    public void showProgress(EventProgressDialog event) {
        if (event.isShow()) {
            showProgressDialog(event.getMessage());
        } else {
            dissmisProgressDialog();
        }
    }


    @CallSuper
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        this.unbinder = ButterKnife.bind(this);
    }



}
