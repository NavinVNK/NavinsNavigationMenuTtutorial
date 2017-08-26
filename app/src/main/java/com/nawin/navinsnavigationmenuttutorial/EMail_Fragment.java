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



@SuppressLint("NewApi")
public class EMail_Fragment extends Fragment {

    Button sendButton;
    Button clearButton;
    EditText textTo;
    EditText textSubject;
    EditText textMessage;
    String subject = null;

    @Override
    public void onAttach(Activity activity) {
        if (!(activity instanceof SubjectMessage))
            throw new ClassCastException();
        subject = ((SubjectMessage) getActivity()).getMessage();
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.email_fragment, container,
                false);
        sendButton = (Button) rootView.findViewById(R.id.sendemailButton);
        clearButton = (Button) rootView.findViewById(R.id.clearmsg);
        textTo = (EditText) rootView.findViewById(R.id.editTextTo);
        textSubject = (EditText) rootView.findViewById(R.id.editTextSubject);
        textMessage = (EditText) rootView.findViewById(R.id.editTextMessage);

        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        textMessage.setText(subject);
        sendButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String to = textTo.getText().toString();
                String subject = textSubject.getText().toString();
                String message = textMessage.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
                // email.putExtra(Intent.EXTRA_CC, new String[]{ to});
                // email.putExtra(Intent.EXTRA_BCC, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                // need this to prompts email client only
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,
                        "Choose an Email client :"));

            }
        });
        clearButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                textMessage.setText("");

            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    void getEMailMessage(String str) {
        subject = str;
    }

}