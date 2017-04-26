package scott.android.com;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import scott.android.com.repository.app.ui.main.use_case.GetThemesUseCase;
import scott.android.com.repository.base.use_case.UseCaseSubscription;
import scott.android.com.repository.data.repositories.ThemeRepository;
import scott.android.com.repository.data.repositories.source.ThemeLocal;
import scott.android.com.repository.data.repositories.source.ThemeRemote;
import scott.android.com.repository.entities.Theme;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author pedroscott. scott7462@gmail.com
 * @version 4/15/17.
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

@RunWith(MockitoJUnitRunner.class)
public class GetThemeTest {

    @Mock
    ThemeLocal local;
    @Mock
    ThemeRemote remote;
    private GetThemesUseCase getThemesUseCase;

    @Before
    public void setUp() {
        getThemesUseCase = new GetThemesUseCase(new ThemeRepository(local, remote));
    }

    @Test
    public void givenAConcreteUseCaseOfGetEuroTeam() {
        assertThat(getThemesUseCase, instanceOf(GetThemesUseCase.class));
    }

    @Test
    public void getTeamListObservableFromRepository() {
        getThemesUseCase.execute(new UseCaseSubscription<List<Theme>>() {
            @Override
            public void onCompleted() {
                super.onCompleted();
                verifyNoMoreInteractions(getThemesUseCase);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(List<Theme> themes) {
                super.onNext(themes);
            }
        });

    }

}
