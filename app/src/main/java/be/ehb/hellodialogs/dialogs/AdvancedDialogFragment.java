package be.ehb.hellodialogs.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class AdvancedDialogFragment extends DialogFragment {

    private DialogConfirmInterface<String> confirmInterface;
    private ArrayList<String> gekozenBeleg = new ArrayList<>();
    private String beleg[] = {"kaas", "tomaat", "sla", "wortel", "mayonaise", "hamburger", "ketchup"};

    //listeners magico
    public interface DialogConfirmInterface<T> {
        void confirm(T[] values);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            confirmInterface = (DialogConfirmInterface<String>) context;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            Log.e("ConformInterface", "Correct interface not implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final boolean gekozen[] = new boolean[beleg.length];
        Arrays.fill(gekozen, false);

        builder.setTitle("Kies uw beleg");
        builder.setMultiChoiceItems(beleg, gekozen, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    gekozenBeleg.add(beleg[which]);
                } else {
                    gekozenBeleg.remove(beleg[which]);
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String values[] = new String[gekozenBeleg.size()];
                for (int i = 0; i < gekozenBeleg.size(); i++) {
                    values[i] = gekozenBeleg.get(i);
                }
                confirmInterface.confirm(values);
            }
        });
        return builder.create();
    }
}
