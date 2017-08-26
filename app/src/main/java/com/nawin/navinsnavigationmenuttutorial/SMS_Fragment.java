package com.nawin.navinsnavigationmenuttutorial;

/**
 * Created by Ganeshan on 8/18/2017.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




@SuppressLint("NewApi")
public class SMS_Fragment extends Fragment {
    Button sendButton;
    Button clearButton;
    EditText textPhoneNo;
    EditText textSMS;

    String message = null;

    @Override
    public void onAttach(Activity activity) {
        if (!(activity instanceof SubjectMessage))
            throw new ClassCastException();
        message = ((SubjectMessage) getActivity()).getMessage();
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sms_fragment, container,
                false);
        sendButton = (Button) rootView.findViewById(R.id.sendsmsbutton);
        clearButton = (Button) rootView.findViewById(R.id.clearsms);
        textPhoneNo = (EditText) rootView.findViewById(R.id.editTextPhoneNo);
        textSMS = (EditText) rootView.findViewById(R.id.editTextSMS);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        textSMS.setText(message);
        sendButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent smsVIntent = new Intent(Intent.ACTION_VIEW);
                // prompts only sms-mms clients

                smsVIntent.setType("vnd.android-dir/mms-sms");
                // extra fields for number and message respectively
                smsVIntent
                        .putExtra("address", textPhoneNo.getText().toString());
                smsVIntent.putExtra("sms_body", textSMS.getText().toString());
                try {
                    startActivity(smsVIntent);
                } catch (Exception ex) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Your sms has failed...", Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            }
        });
        clearButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                textSMS.setText("");

            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    void getSMSMessage(String str) {
        message = str;
    }
}
