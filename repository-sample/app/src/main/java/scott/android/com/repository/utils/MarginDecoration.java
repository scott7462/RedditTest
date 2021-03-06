package scott.android.com.repository.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import scott.android.com.repository.R;
import scott.android.com.repository.app.App;

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
public class MarginDecoration extends RecyclerView.ItemDecoration {

    private int margin;

    public MarginDecoration() {
        margin = App.getGlobalContext().getResources().getDimensionPixelSize(R.dimen.default_small_size);
    }

    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = margin;
        outRect.right = margin;
        outRect.bottom = margin;
        outRect.top = margin;
    }

    public MarginDecoration(int margin) {
        this.margin = margin;
    }
}
