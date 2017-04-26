package scott.android.com.repository.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import scott.android.com.repository.app.ui.main.MainFragment;
import scott.android.com.repository.di.modules.MainModule;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(MainFragment fragment);

    Context context();
}
