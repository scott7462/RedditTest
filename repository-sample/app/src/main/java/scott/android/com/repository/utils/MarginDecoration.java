package scott.android.com.repository.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import scott.android.com.repository.R;
import scott.android.com.repository.app.App;

/**
 * @author pedroscott. scott7462@gmail.com
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
