package com.nawin.navinsnavigationmenuttutorial;

/**
 * Created by Ganeshan on 8/18/2017.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("NewApi")
public class Message_Fragment extends Fragment {
    Button smsButton;
    Button emailButton;
    EditText messageEditText;

    SubjectMessage messageInterface;

    @Override
    public void onAttach(Activity activity) {
        // We verify that our activity implements the listener
        if (!(activity instanceof SubjectMessage))
            throw new ClassCastException();
        else
            messageInterface = (SubjectMessage) getActivity();
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.message, container,
                false);
        smsButton = (Button) rootView.findViewById(R.id.smsbutton);
        emailButton = (Button) rootView.findViewById(R.id.emailbutton);
        messageEditText = (EditText) rootView.findViewById(R.id.message);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        smsButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                messageInterface.setMessage(messageEditText.getText()
                        .toString(), 1);

            }
        });
        emailButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                messageInterface.setMessage(messageEditText.getText()
                        .toString(), 2);

            }
        });
        super.onActivityCreated(savedInstanceState);
    }

}