package be.ehb.hellodialogs.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import be.ehb.hellodialogs.R;
import be.ehb.hellodialogs.dialogs.AdvancedDialogFragment;
import be.ehb.hellodialogs.dialogs.SimpleDialogFragment;

public class MainActivity extends AppCompatActivity
        implements SimpleDialogFragment.DialogCancelInterface, AdvancedDialogFragment.DialogConfirmInterface<String> {

    private final String DIALOG_KEY = "dialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSimpleDialog = findViewById(R.id.btn_simple_dialog);
        btnSimpleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialogFragment sdf = new SimpleDialogFragment();
                sdf.show(getSupportFragmentManager(), DIALOG_KEY);
            }
        });

        Button btnAdvancedDialog = findViewById(R.id.btn_advanced_dialog);
        btnAdvancedDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvancedDialogFragment adf = new AdvancedDialogFragment();
                adf.show(getSupportFragmentManager(), DIALOG_KEY);
            }
        });
    }

    @Override
    public void cancel() {
        Toast.makeText(this, R.string.str_toast_cancel, Toast.LENGTH_LONG).show();
    }

    @Override
    public void confirm(String[] values) {
        for(String element : values)
            Log.d("GEKOZEN", element);

        Snackbar sb = Snackbar.make(findViewById(R.id.container), "Bestelling binnen", Snackbar.LENGTH_LONG);
        sb.setActionTextColor(Color.RED);
        sb.setAction("cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GEKOZEN", "alles geannuleerd");
            }
        });
        sb.show();
    }
}
