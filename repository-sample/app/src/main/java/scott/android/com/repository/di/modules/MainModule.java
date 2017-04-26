package scott.android.com.repository.di.modules;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import scott.android.com.repository.app.App;
import scott.android.com.repository.data.repositories.ThemeDataSource;
import scott.android.com.repository.data.repositories.ThemeRepository;
import scott.android.com.repository.data.repositories.source.ThemeLocal;
import scott.android.com.repository.data.repositories.source.ThemeRemote;

@Module
public class MainModule {

    private final App app;

    public MainModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    ThemeDataSource provideRepository(ThemeRepository repository) {
        return repository;
    }

    @Provides
    @Named(ThemeRepository.LOCAL_NAMED)
    ThemeDataSource provideThemeLocal() {
        return new ThemeLocal();
    }

    @Provides
    @Named(ThemeRepository.REMOTE_NAMED)
    ThemeDataSource provideThemeRemote() {
        return new ThemeRemote();
    }

}
