package com.example.android.lab11_dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {

    public interface 想收對話框結果的{
        void 收結果(String username);
    }

    private 想收對話框結果的 mSomeone;
    //setter
    public void set想收對話框結果的(想收對話框結果的 someone){
        this.mSomeone = someone;
    }

    private EditText m_et_username;

    public MyDialogFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_my_dialog,null);
        m_et_username = (EditText)view.findViewById(R.id.et_username);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        String message = "歡迎光臨" + m_et_username.getText();
//                        ((TextView)getActivity().findViewById(R.id.tv_message)).setText(message);
                        if(mSomeone != null){
                            mSomeone.收結果(m_et_username.getText().toString());
                        }

                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        ((TextView)getActivity().findViewById(R.id.tv_message)).setText("登入取消");
                        if (mSomeone != null){
                            mSomeone.收結果(null);
                        }

                    }
                });
        return builder.create();
    }
}


