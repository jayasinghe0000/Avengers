package com.example.avengers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class MessageActivity extends AppCompatActivity {
    Dialog myDialog;
    private EditText editTextNumber;
    private EditText editTextMessage;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_m);
        myDialog = new Dialog(this);

//        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);



        awesomeValidation.addValidation(this,R.id.editTextNumber,"[0-9][10]$",R.string.invalid_mobile);
    }

    public void ShowPopup(View v) {
        TextView txtclose;
        Button btnFollow;
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        editTextMessage = myDialog.findViewById(R.id.editTextTextMultiLine);
        editTextNumber = myDialog.findViewById(R.id.editTextNumber);

        myDialog.setContentView(R.layout.popup_message);
        awesomeValidation.addValidation(this,R.id.editTextNumber,"[0-9][10]$",R.string.invalid_mobile);

        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");

        btnFollow = (Button) myDialog.findViewById(R.id.button);
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(MessageActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

            }
        });

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

//        btnFollow.se
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void sendSMS(View view) {
        if (awesomeValidation.validate()) {
            String message = editTextMessage.getText().toString();
            String number = editTextNumber.getText().toString();

            SmsManager mySmsManager = SmsManager.getDefault();
            mySmsManager.sendTextMessage(number, null, message, null, null);

            Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                    Toast.LENGTH_LONG).show();
        }
    }
}