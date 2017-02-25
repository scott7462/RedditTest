package scott.android.com.reddittest.data.repositories.theme.source;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import scott.android.com.reddittest.app.App;
import scott.android.com.reddittest.data.repositories.theme.ThemeDataSours;
import scott.android.com.reddittest.data.managers.db.DBSQLiteHelper;
import scott.android.com.reddittest.data.managers.db.tables.ThemeTable;
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


public class ThemeLocal implements ThemeDataSours {

    private static ThemeLocal instance = null;


    private static DBSQLiteHelper sqlExternalHelper;

    public static DBSQLiteHelper getDBSQLHelper() {
        if (sqlExternalHelper == null) {
            sqlExternalHelper = new DBSQLiteHelper(App.getGlobalContext());
        }
        return sqlExternalHelper;
    }


    public static ThemeLocal newInstance() {
        if (instance == null) {
            instance = new ThemeLocal();
        }
        return instance;
    }

    @Override
    public Observable<List<Theme>> getThemes() {
        return Observable.create(new Observable.OnSubscribe<List<Theme>>() {
            @Override
            public void call(Subscriber<? super List<Theme>> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    try {
                        Dao<ThemeTable, Long> themeDao = getDBSQLHelper().getThemeDao();
                        List<ThemeTable> themeTables = themeDao.queryBuilder().query();
                        List<Theme> themes = new ArrayList<>();
                        for (ThemeTable themeTable : themeTables) {
                            themes.add(Theme.newBuilder()
                                    .withId(themeTable.getId())
                                    .withTitle(themeTable.getTitle())
                                    .withDescription(themeTable.getDescription())
                                    .withName(themeTable.getName())
                                    .withBannerImg(themeTable.getBannerImg())
                                    .withDisplayName(themeTable.getDisplayName())
                                    .withCreated(themeTable.getCreated())
                                    .withIconImg(themeTable.getIconImg())
                                    .withLang(themeTable.getLang())
                                    .withSubmitText(themeTable.getSubmitText())
                                    .withSubscribers(themeTable.getSubscribers())
                                    .build());
                        }
                        subscriber.onNext(themes);
                    } catch (SQLException e) {
                        subscriber.onError(e);
                        e.printStackTrace();
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

    @Override
    public Observable<Boolean> cleanThemes() {
        return Observable.just(true)
                .flatMap(new Func1<Boolean, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(Boolean aBoolean) {
                        try {
                            Dao<ThemeTable, Long> themeTables = getDBSQLHelper().getThemeDao();
                            themeTables.delete(themeTables.queryForAll());
                        } catch (SQLException e) {
                            e.printStackTrace();
                            Observable.error(e);
                        }
                        return Observable.just(true);
                    }
                });
    }

    @Override
    public Observable<List<Theme>> saveThemes(List<Theme> themes) {
        return Observable.from(themes)
                .flatMap(new Func1<Theme, Observable<Theme>>() {
                    @Override
                    public Observable<Theme> call(Theme theme) {
                        return addOrRemoveFavorite(theme);
                    }
                }).toList();
    }

    private Observable<Theme> addOrRemoveFavorite(final Theme theme) {
        return Observable.just(theme)
                .flatMap(new Func1<Theme, Observable<Theme>>() {
                    @Override
                    public Observable<Theme> call(Theme theme) {
                        try {
                            Dao<ThemeTable, Long> themeTables = getDBSQLHelper().getThemeDao();
                            themeTables
                                    .createOrUpdate(ThemeTable.newBuilder()
                                            .withId(theme.getId())
                                            .withTitle(theme.getTitle())
                                            .withDescription(theme.getDescription())
                                            .withName(theme.getName())
                                            .withBannerImg(theme.getBannerImg())
                                            .withDisplayName(theme.getDisplayName())
                                            .withCreated(theme.getCreated())
                                            .withIconImg(theme.getIconImg())
                                            .withLang(theme.getLang())
                                            .withSubmitText(theme.getSubmitText())
                                            .withSubscribers(theme.getSubscribers())
                                            .build());
                        } catch (SQLException e) {
                            e.printStackTrace();
                            Observable.error(e);
                        }
                        return Observable.just(theme);
                    }
                });
    }

}
