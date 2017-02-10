package scott.android.com.reddittest.app.ui.main;

import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import scott.android.com.reddittest.R;
import scott.android.com.reddittest.base.view.BaseSimpleAdapter;
import scott.android.com.reddittest.entities.Theme;

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
public class ThemeAdapter extends BaseSimpleAdapter<Theme, BaseSimpleAdapter.BaseViewHolder> {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY_VIEW: {
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme_empty, parent, false));
            }
            case LOADING_VIEW: {
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme_loading, parent, false));
            }
            default:
                return new ThemeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false));
        }
    }

    class ThemeHolder extends BaseViewHolder<Theme> {

        @BindView(R.id.iVFrgHomeTheme)
        ImageView iVFrgHomeTheme;
        @BindView(R.id.tVFrgHomeThemeTitle)
        TextView tVFrgHomeThemeTitle;
        @BindView(R.id.tVFrgHomeThemeDescription)
        TextView tVFrgHomeThemeDescription;

        public ThemeHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(Theme item) {
            tVFrgHomeThemeTitle.setText(item.getDisplayName());
            tVFrgHomeThemeDescription.setText(Html.fromHtml(item.getDescription()));
            Glide.with(iVFrgHomeTheme.getContext())
                    .load(item.getBannerImg())
                    .error(ContextCompat.getDrawable(iVFrgHomeTheme.getContext(), R.drawable.bg_placeholder))
                    .centerCrop()
                    .into(iVFrgHomeTheme);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callItemListenerByPosition(getAdapterPosition());
                }
            });
        }
    }

}
