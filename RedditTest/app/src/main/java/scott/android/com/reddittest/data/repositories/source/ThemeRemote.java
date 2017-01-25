package scott.android.com.reddittest.data.repositories.source;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import scott.android.com.reddittest.R;
import scott.android.com.reddittest.app.App;
import scott.android.com.reddittest.data.repositories.ThemeDataSours;
import scott.android.com.reddittest.data.source.rest.RestClientPublic;
import scott.android.com.reddittest.data.source.rest.response.ResponseReddit;
import scott.android.com.reddittest.data.source.rest.response.RedditTheme;
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


public class ThemeRemote implements ThemeDataSours {


    private static RestClientPublic restClientPublic;

    public static RestClientPublic getRestClientPublic() {
        return restClientPublic;
    }

    public ThemeRemote() {
        restInt();
    }

    private void restInt() {
        restClientPublic = new RestClientPublic(App.getGlobalContext().getString(R.string.base_url));
    }

    private static ThemeRemote instance = null;

    public static ThemeRemote newInstance() {
        if (instance == null) {
            instance = new ThemeRemote();
        }
        return instance;
    }

    @Override
    public Observable<List<Theme>> getThemes() {
        return getRestClientPublic().getPublicService().getThemes()
                .flatMap(new Func1<ResponseReddit, Observable<RedditTheme>>() {
                    @Override
                    public Observable<RedditTheme> call(ResponseReddit responseReddit) {
                        return Observable.from(responseReddit.getData().getChildren());
                    }
                })
                .flatMap(new Func1<RedditTheme, Observable<Theme>>() {
                    @Override
                    public Observable<Theme> call(RedditTheme redditTheme) {
                        return Observable.just(redditTheme.getTheme());
                    }
                }).toList();
    }

    @Override
    public Observable<Boolean> cleanThemes() {
        return null;
    }

    @Override
    public Observable<List<Theme>> saveThemes(List<Theme> themes) {
        return null;
    }
}
