package scott.android.com.repository.data;


import scott.android.com.repository.data.repositories.ThemeRepository;
import scott.android.com.repository.data.repositories.source.ThemeLocal;
import scott.android.com.repository.data.repositories.source.ThemeLocalRealm;
import scott.android.com.repository.data.repositories.source.ThemeRemote;

/**
 * @author pedroscott. scott7462@gmail.com
 * @version 1/19/17.
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
public class Injection {

    public static ThemeRepository provideThemeRepository() {
        return ThemeRepository.newInstance(ThemeLocalRealm.newInstance(), ThemeRemote.newInstance());
    }

}
