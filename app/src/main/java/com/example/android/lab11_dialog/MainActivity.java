package com.example.android.lab11_dialog;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
implements DialogInterface.OnClickListener
        , MyDialogFragment.想收對話框結果的{

    private TextView m_tv_message;
    private int mChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        m_tv_message = (TextView)findViewById(R.id.tv_message);
    }

    public void clickAlertDialog(View view){
        new AlertDialog.Builder(this)
                .setMessage("㪋帥")
                .setPositiveButton("知道",this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        m_tv_message.setText("知道ㄌ");
    }

    public void clickAlertDialogYesNo(View view) {
        AlertDialogYesNoLIstener listener = new AlertDialogYesNoLIstener();
        new AlertDialog.Builder(this)
                .setMessage("㪋帥")
                .setPositiveButton("知道",listener)
                .setNegativeButton("狗腿",listener)
                .show();
    }

    private class AlertDialogYesNoLIstener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog,int which){
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    m_tv_message.setText("細屑");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    m_tv_message.setText("狗腿");
                    break;
            }
        }
    }

    public void clickAlertDialogYesNoCancel(View view) {
        new AlertDialog.Builder(this)
                .setMessage("好帥")
                .setPositiveButton("謝謝",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        m_tv_message.setText("謝謝");
                    }
                })
                .setNegativeButton("狗ㄊㄨㄟˇ" ,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        m_tv_message.setText("狗腿");
                    }
                })
                .setNeutralButton("不知道",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which) {
                m_tv_message.setText("補知道");
            }
        })
        .show();
    }

    public void clickAlertDialogItems(View view) {
        final String[] response = getResources().getStringArray(R.array.response);
        new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar)
                .setTitle("你好帥")
                .setItems(response, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        m_tv_message.setText(response[which]);
                    }
                })
                .show();
    }

    public void clickAlerDialogMultiChoice(View view) {
        final String[] response = getResources().getStringArray(R.array.response);
        final boolean[] selected = new boolean[response.length];

        new AlertDialog.Builder(this)
                .setTitle("你好帥")
                .setMultiChoiceItems(response,selected,
                        new DialogInterface.OnMultiChoiceClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which,boolean isChecked){
                                selected[which] = isChecked;
                            }
                        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        StringBuilder result = new StringBuilder();
                        for (int i = 0;i <selected.length;i++){
                            if (selected[i]){
                                result.append(response[i]).append("\n");
                            }
                        }
                        m_tv_message.setText(result);
                    }
                })
                .setNegativeButton("CANCEL",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("無語");
                    }
        })
                .show();

    }

    public void clickAlerDialogSingleChoice(View view) {
        final String[] response = getResources().getStringArray(R.array.response);
        mChoice = 0;
        new AlertDialog.Builder(this)
                .setTitle("你好帥")
                .setSingleChoiceItems(response, mChoice, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mChoice = which;
                    }
                })
                .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText(response[mChoice]);
                    }
                })
                .setNegativeButton("CACEL", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("無語");
                    }
                })
                .show();
    }

    public void clickMyDialogFragment(View view) {
        MyDialogFragment dialog = new MyDialogFragment();
        dialog.set想收對話框結果的(this);
        dialog.show(getSupportFragmentManager(), "MyDialogFragment");
    }

    @Override
    public void 收結果(String username) {
        if (username == null){
            m_tv_message.setText("取消登入");
        }else {
            m_tv_message.setText("歡迎光臨"+username);
        }

    }
}
