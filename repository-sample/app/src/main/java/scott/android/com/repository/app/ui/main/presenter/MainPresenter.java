package scott.android.com.repository.app.ui.main.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import scott.android.com.repository.app.ui.main.use_case.GetThemesUseCase;
import scott.android.com.repository.base.presenter.BasePresenter;
import scott.android.com.repository.base.use_case.UseCaseSubscription;
import scott.android.com.repository.entities.Theme;

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

    private GetThemesUseCase getThemesUseCase;

    @Inject
    public MainPresenter(@NonNull GetThemesUseCase getThemesUseCase) {
        this.getThemesUseCase = getThemesUseCase;
    }

    public void doGetTheme() {
        getThemesUseCase.execute(
                new UseCaseSubscription<List<Theme>>() {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(List<Theme> themes) {
                        super.onNext(themes);
                        getViewListener().itemsLoaded(themes);
                    }
                });
    }
}
