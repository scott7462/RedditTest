package scott.android.com.repository.data.managers.db.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import scott.android.com.repository.data.managers.db.DBConstraints;
import scott.android.com.repository.entities.Theme;

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
@DatabaseTable(tableName = DBConstraints.TABLE_THEME)
public class ThemeTable {

    @DatabaseField(columnName = DBConstraints.TABLE_THEME_ID, id = true)
    private String id;
    @DatabaseField(columnName = DBConstraints.TABLE_THEME_BANNER_IMG)
    private String bannerImg;
    @DatabaseField(columnName = DBConstraints.TABLE_THEME_SUBMIT_TEXT)
    private String submitText;
    @DatabaseField(columnName = DBConstraints.TABLE_THEME_DISPLAY_NAME)
    private String displayName;
    @DatabaseField(columnName = DBConstraints.TABLE_THEME_TITLE)
    private String title;
    @DatabaseField(columnName = DBConstraints.TABLE_THEME_IMAGE_ICON)
    private String iconImg;
    @DatabaseField(columnName = DBConstraints.TABLE_THEME_DESCRIPTION)
    private String description;
    @DatabaseField(columnName = DBConstraints.TABLE_THEME_PUBLIC_TRAFFIC)
    private boolean publicTraffic;
    @DatabaseField(columnName = DBConstraints.TABLE_THEME_SUBSCRIBERS)
    private int subscribers;
    @DatabaseField(columnName = DBConstraints.TABLE_THEME_LANG)
    private String lang;
    @DatabaseField(columnName = DBConstraints.TABLE_THEME_NAME)
    private String name;
    @DatabaseField(columnName = DBConstraints.TABLE_THEME_CREATED)
    private int created;

    public ThemeTable() {
    }

    private ThemeTable(Builder builder) {
        setId(builder.id);
        setBannerImg(builder.bannerImg);
        setSubmitText(builder.submitText);
        setDisplayName(builder.displayName);
        setTitle(builder.title);
        setIconImg(builder.iconImg);
        setDescription(builder.description);
        setPublicTraffic(builder.publicTraffic);
        setSubscribers(builder.subscribers);
        setLang(builder.lang);
        setName(builder.name);
        setCreated(builder.created);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getSubmitText() {
        return submitText;
    }

    public void setSubmitText(String submitText) {
        this.submitText = submitText;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublicTraffic() {
        return publicTraffic;
    }

    public void setPublicTraffic(boolean publicTraffic) {
        this.publicTraffic = publicTraffic;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public static Theme toTheme(ThemeTable themeTable) {
        return Theme.newBuilder()
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
                .build();
    }

    public static final class Builder {
        private String id;
        private String bannerImg;
        private String submitText;
        private String displayName;
        private String title;
        private String iconImg;
        private String description;
        private boolean publicTraffic;
        private int subscribers;
        private String lang;
        private String name;
        private int created;

        private Builder() {
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public Builder withBannerImg(String val) {
            bannerImg = val;
            return this;
        }

        public Builder withSubmitText(String val) {
            submitText = val;
            return this;
        }

        public Builder withDisplayName(String val) {
            displayName = val;
            return this;
        }

        public Builder withTitle(String val) {
            title = val;
            return this;
        }

        public Builder withIconImg(String val) {
            iconImg = val;
            return this;
        }

        public Builder withDescription(String val) {
            description = val;
            return this;
        }

        public Builder withPublicTraffic(boolean val) {
            publicTraffic = val;
            return this;
        }

        public Builder withSubscribers(int val) {
            subscribers = val;
            return this;
        }

        public Builder withLang(String val) {
            lang = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withCreated(int val) {
            created = val;
            return this;
        }

        public ThemeTable build() {
            return new ThemeTable(this);
        }

        public ThemeTable withTheme(Theme theme) {
            return ThemeTable.newBuilder()
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
                    .build();
        }

    }
}
