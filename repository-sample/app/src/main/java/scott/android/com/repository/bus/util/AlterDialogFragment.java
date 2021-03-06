package scott.android.com.repository.bus.util;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import scott.android.com.repository.bus.event.EventAlterDialog;


/**
 * @author pedroscott. scott7462@gmail.com
 * @version 9/11/16.
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
public class AlterDialogFragment extends DialogFragment {

    EventAlterDialog eventAlterDialog = new EventAlterDialog();

    public void setEventAlterDialog(EventAlterDialog eventAlterDialog) {
        this.eventAlterDialog = eventAlterDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return AlterDialogUtils.makeAlterDialog(eventAlterDialog, getActivity());
    }
}
