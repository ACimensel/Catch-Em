package com.android.cmpt276as3.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.cmpt276as3.R;

public class CongratulationMessageFragment extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Create the view to show
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.congratulation_message_layout, null);


        Button btn = (Button) v.findViewById(R.id.btnReturnToMainMenu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = MainMenu.makeLaunchIntent(getActivity());
                startActivity(i);
            }
        });


        // Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();

    }
}