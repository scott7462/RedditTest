package scott.android.com.reddittest.data.repositories.theme;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import scott.android.com.reddittest.entities.Theme;

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


public class ThemeRepository implements ThemeDataSours {

    ThemeDataSours local;
    ThemeDataSours remote;

    private static ThemeRepository instance = null;

    public ThemeRepository(ThemeDataSours local, ThemeDataSours remote) {
        this.local = local;
        this.remote = remote;
    }

    public static ThemeRepository newInstance(ThemeDataSours local, ThemeDataSours remote) {
        if (instance == null) {
            instance = new ThemeRepository(local, remote);
        }
        return instance;
    }

    @Override
    public Observable<List<Theme>> getThemes() {
        return remote.getThemes()
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Theme>>>() {
                    @Override
                    public Observable<? extends List<Theme>> call(Throwable throwable) {
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
