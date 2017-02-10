package scott.android.com.reddittest.app.ui.main.presenter;

import java.util.List;

import rx.Subscriber;
import scott.android.com.reddittest.base.presenter.BasePresenter;
import scott.android.com.reddittest.data.Injection;
import scott.android.com.reddittest.entities.Theme;
import timber.log.Timber;

/**
 * @author pedroscott. scott7462@gmail.com
 * @version 1/15/17.
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


public class MainPresenter extends BasePresenter<MainPresenterListener> {

    public void doGetTheme() {
        addSubscription(Injection.provideThemeRepository()
                .getThemes().subscribe(new Subscriber<List<Theme>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Theme> themes) {
                        getViewListener().itemsLoaded(themes);
                    }
                }));

    }
}
