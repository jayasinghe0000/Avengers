package com.example.avengers;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PopupEmail extends AppCompatActivity {
    Dialog myDialog;
    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        myDialog = new Dialog(PopupEmail.this);

    }

    public void ShowPopup(View v) {

        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.activity_custom_popup_email);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        btnFollow = (Button) myDialog.findViewById(R.id.button_send);

        mEditTextTo = myDialog.findViewById(R.id.edit_text_to);
        mEditTextSubject = myDialog.findViewById(R.id.edit_text_subject);
        mEditTextMessage = myDialog.findViewById(R.id.edit_text_message);
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    private void sendMail() {
        String recipientList = mEditTextTo.getText().toString();

        if(mEditTextTo.length() == 0){
            mEditTextTo.requestFocus();
            mEditTextTo.setError("Enter email");
        }else{
            String[] recipients = recipientList.split(",");
            //example1@gmail.com,example2@gmail.com

            String subject = mEditTextSubject.getText().toString();
            String message = mEditTextMessage.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);

            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Choose an email client"));
        }

    }

}