package scott.android.com.repository.data.repositories.source;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import scott.android.com.repository.data.managers.rest.RestClientPublic;
import scott.android.com.repository.data.managers.rest.response.RedditTheme;
import scott.android.com.repository.data.managers.rest.response.ResponseReddit;
import scott.android.com.repository.data.repositories.ThemeDataSource;
import scott.android.com.repository.entities.Theme;

/**
 * Always need implement the DataSource Interfaces of the repository
 * to get the methods of the repository behavior
 */
final class ThemeRemoteDoc implements ThemeDataSource {

    private static ThemeRemoteDoc instance = null;

    public static ThemeRemoteDoc newInstance() {
        if (instance == null) {
            instance = new ThemeRemoteDoc();
        }
        return instance;
    }
    /**
     * Set the direct dependency o
     */
    private static RestClientPublic getRestClientPublic() {
        return RestClientPublic.getInstance();
    }
    /**
     * Always need implement the DataSource Interfaces of the repository
     * to get the methods of the repository behavior
     */
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
