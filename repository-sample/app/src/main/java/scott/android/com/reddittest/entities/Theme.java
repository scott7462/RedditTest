
package scott.android.com.reddittest.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
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
public class Theme implements Parcelable {

    public static final String THEME_ARG = Theme.class.getName();

    @SerializedName("banner_img")
    @Expose
    private String bannerImg;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("submit_text")
    @Expose
    private String submitText;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("icon_img")
    @Expose
    private String iconImg;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("public_traffic")
    @Expose
    private boolean publicTraffic;
    @SerializedName("subscribers")
    @Expose
    private int subscribers;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created")
    @Expose
    private int created;

    private Theme(Builder builder) {
        setBannerImg(builder.bannerImg);
        setId(builder.id);
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

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bannerImg);
        dest.writeString(this.id);
        dest.writeString(this.submitText);
        dest.writeString(this.displayName);
        dest.writeString(this.title);
        dest.writeString(this.iconImg);
        dest.writeString(this.description);
        dest.writeByte(this.publicTraffic ? (byte) 1 : (byte) 0);
        dest.writeInt(this.subscribers);
        dest.writeString(this.lang);
        dest.writeString(this.name);
        dest.writeInt(this.created);
    }

    public Theme() {
    }

    protected Theme(Parcel in) {
        this.bannerImg = in.readString();
        this.id = in.readString();
        this.submitText = in.readString();
        this.displayName = in.readString();
        this.title = in.readString();
        this.iconImg = in.readString();
        this.description = in.readString();
        this.publicTraffic = in.readByte() != 0;
        this.subscribers = in.readInt();
        this.lang = in.readString();
        this.name = in.readString();
        this.created = in.readInt();
    }

    public static final Creator<Theme> CREATOR = new Creator<Theme>() {
        @Override
        public Theme createFromParcel(Parcel source) {
            return new Theme(source);
        }

        @Override
        public Theme[] newArray(int size) {
            return new Theme[size];
        }
    };


    public static final class Builder {
        private String bannerImg;
        private String id;
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

        public Builder withBannerImg(String val) {
            bannerImg = val;
            return this;
        }

        public Builder withId(String val) {
            id = val;
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

        public Theme build() {
            return new Theme(this);
        }
    }
}
