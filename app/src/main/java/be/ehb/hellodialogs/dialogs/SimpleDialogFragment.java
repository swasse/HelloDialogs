package be.ehb.hellodialogs.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import be.ehb.hellodialogs.R;

public class SimpleDialogFragment extends DialogFragment {

    private DialogCancelInterface cancelInterface;
    public interface DialogCancelInterface {
        void cancel();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            cancelInterface = (DialogCancelInterface) context;
        }
        catch(ClassCastException ex)
        {
            ex.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.str_simple_dialog_title);
        builder.setMessage(R.string.str_simple_dialog_description);
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelInterface.cancel();
            }
        });

        return builder.create();
    }
}
