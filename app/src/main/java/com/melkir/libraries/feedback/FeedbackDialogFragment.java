package com.melkir.libraries.feedback;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.melkir.libraries.R;

public class FeedbackDialogFragment extends DialogFragment {

    private EditText mInputSubject, mInputContent;
    private TextInputLayout mInputLayoutSubject, mInputLayoutContent;

    @SuppressLint("InflateParams")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_feedback, null);

        // Set input listeners
        mInputLayoutSubject = (TextInputLayout) view.findViewById(R.id.input_layout_subject);
        mInputLayoutContent = (TextInputLayout) view.findViewById(R.id.input_layout_content);
        mInputSubject = (EditText) view.findViewById(R.id.input_subject);
        mInputContent = (EditText) view.findViewById(R.id.input_content);
        mInputSubject.addTextChangedListener(new MyTextWatcher(mInputSubject));
        mInputContent.addTextChangedListener(new MyTextWatcher(mInputContent));

        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(R.string.button_text_send, null)
                .create();

        dialog.setOnShowListener(alertDialog -> {
            Button button = ((AlertDialog) alertDialog).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(v -> {
                //Dismiss once everything is OK.
                if (submitForm()) alertDialog.dismiss();
            });
        });
        return dialog;
    }

    /**
     * Validating form
     */
    private boolean submitForm() {
        if (!validateSubject() || !validateContent()) {
            return false;
        } else {
            String subject = mInputSubject.getText().toString();
            String body = mInputContent.getText().toString();
            sendEmail(subject, body);
            Toast.makeText(getActivity(), getString(R.string.feedback_thanks), Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private boolean validateSubject() {
        if (mInputSubject.getText().toString().trim().isEmpty()) {
            mInputLayoutSubject.setError(getString(R.string.err_msg_field));
            requestFocus(mInputSubject);
            return false;
        } else {
            mInputLayoutSubject.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateContent() {
        if (mInputContent.getText().toString().trim().isEmpty()) {
            mInputLayoutContent.setError(getString(R.string.err_msg_field));
            requestFocus(mInputContent);
            return false;
        } else {
            mInputLayoutContent.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        Window window = getDialog().getWindow();
        if (window != null && view.requestFocus()) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {
        private final View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_subject:
                    validateSubject();
                    break;
                case R.id.input_content:
                    validateContent();
                    break;
            }
        }
    }

    private void sendEmail(String subject, String body) {
//        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//        String[] recipients = new String[]{"melkir13@gmail.com"};
//        emailIntent.setData(Uri.parse("mailto:"));
//        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
//        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
//        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
//        startActivity(Intent.createChooser(emailIntent, "Send feedback"));
    }
}
