package scott.android.com.repository.data.repositories;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import scott.android.com.repository.entities.Theme;

/**
 * @author pedroscott. scott7462@gmail.com
 * @version 1/23/17.
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
@Singleton
public class ThemeRepository implements ThemeDataSource {

    public static final String LOCAL_NAMED = "theme_local";
    public static final String REMOTE_NAMED = "theme_remote";
    private ThemeDataSource local;
    private ThemeDataSource remote;

    @Inject
    public ThemeRepository(@Named(LOCAL_NAMED) @NonNull ThemeDataSource local,
                           @Named(REMOTE_NAMED) @NonNull ThemeDataSource remote) {
        this.local = local;
        this.remote = remote;
    }

    @Override
    public Observable<List<Theme>> getThemes() {
        return remote.getThemes()
                .onErrorResumeNext(new Func1<Throwable, Observable<List<Theme>>>() {
                    @Override
                    public Observable<List<Theme>> call(Throwable throwable) {
                        return local.getThemes();
                    }
                })
                .flatMap(new Func1<List<Theme>, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(List<Theme> themes) {
                        return cleanThemes();
                    }
                }, new Func2<List<Theme>, Boolean, List<Theme>>() {
                    @Override
                    public List<Theme> call(List<Theme> themes, Boolean aBoolean) {
                        return themes;
                    }
                }).flatMap(new Func1<List<Theme>, Observable<List<Theme>>>() {
                    @Override
                    public Observable<List<Theme>> call(List<Theme> themes) {
                        return saveThemes(themes);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Boolean> cleanThemes() {
        return local.cleanThemes();
    }

    @Override
    public Observable<List<Theme>> saveThemes(List<Theme> themes) {
        return local.saveThemes(themes);
    }
}
