package com.example.drew.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by drew on 2/9/15.
 */
public class MOMA extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.MOMA_text)
                .setPositiveButton(R.string.MOMA_visit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      Intent browse = new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                      Intent chooser = Intent.createChooser( browse, getResources().getString(R.string.open));
                      startActivity(chooser);
                    }
                })
                .setNegativeButton(R.string.MOMA_not_now, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }




}
