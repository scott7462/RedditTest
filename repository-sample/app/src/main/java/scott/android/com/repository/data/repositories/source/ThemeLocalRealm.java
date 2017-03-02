package scott.android.com.repository.data.repositories.source;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;
import scott.android.com.repository.data.managers.db_realm.RealmObservable;
import scott.android.com.repository.data.managers.db_realm.tables.ThemeTable;
import scott.android.com.repository.data.repositories.ThemeDataSource;
import scott.android.com.repository.entities.Theme;

/**
 * @author pedroscott. scott7462@gmail.com
 * @version 2/25/17.
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
public class ThemeLocalRealm implements ThemeDataSource {

    private static ThemeLocalRealm instance = null;

    public static ThemeLocalRealm newInstance() {
        if (instance == null) {
            instance = new ThemeLocalRealm();
        }
        return instance;
    }

    @Override
    public Observable<List<Theme>> getThemes() {
        return RealmObservable
                .results(new Func1<Realm, RealmResults<ThemeTable>>() {
                    @Override
                    public RealmResults<ThemeTable> call(Realm realm) {
                        return realm.where(ThemeTable.class).findAll();
                    }
                }).flatMap(new Func1<RealmResults<ThemeTable>, Observable<List<Theme>>>() {
                    @Override
                    public Observable<List<Theme>> call(RealmResults<ThemeTable> results) {
                        List<Theme> themes = new ArrayList<>();
                        for (ThemeTable result : results) {
                            themes.add(ThemeTable.toTheme(result));
                        }
                        return Observable.just(themes);
                    }
                });
    }

    @Override
    public Observable<Boolean> cleanThemes() {
        return RealmObservable.remove(new Func1<Realm, Boolean>() {
            @Override
            public Boolean call(Realm realm) {
                return realm.where(ThemeTable.class).findAll().deleteAllFromRealm();
            }
        });
    }

    @Override
    public Observable<List<Theme>> saveThemes(List<Theme> themes) {
        return Observable.from(themes)
                .flatMap(new Func1<Theme, Observable<Theme>>() {
                    @Override
                    public Observable<Theme> call(final Theme theme) {
                        return RealmObservable.object(new Func1<Realm, ThemeTable>() {
                            @Override
                            public ThemeTable call(Realm realm) {
                                return realm.copyToRealmOrUpdate(ThemeTable.newBuilder().withTheme(theme));
                            }
                        }).flatMap(new Func1<ThemeTable, Observable<Theme>>() {
                            @Override
                            public Observable<Theme> call(ThemeTable themeTable) {
                                return Observable.just(ThemeTable.toTheme(themeTable));
                            }
                        });
                    }
                }).toList();
    }


}
