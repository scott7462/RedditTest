package scott.android.com.reddittest.data.managers.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import scott.android.com.reddittest.R;
import scott.android.com.reddittest.data.managers.db.tables.ThemeTable;

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
public class DBSQLiteHelper extends OrmLiteSqliteOpenHelper {

    private Dao<ThemeTable, Long> themeDao;

    public DBSQLiteHelper(Context context) {
        super(context, context.getString(R.string.db_name), null,
                context.getResources().getInteger(R.integer.db_version_number));
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ThemeTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
    }

    public Dao<ThemeTable, Long> getThemeDao() throws SQLException {
        if (themeDao == null) {
            themeDao = getDao(ThemeTable.class);
        }
        return themeDao;
    }

}
