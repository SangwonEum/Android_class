package com.ch1a;

import com.example.androidprj.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogSample03 extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_main);
        
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("제목");
        
        TextView text = (TextView) dialog.findViewById(R.id.message);
        text.setText("커스텀 다이얼로그입니다.");
        ImageView icon = (ImageView) dialog.findViewById(R.id.icon);
        icon.setImageResource(R.drawable.ic_launcher);


        //커스텀 다이알로그에 버튼을 추가한다.
        /*View layout = getLayoutInflater().inflate(R.layout.custom_dialog, (ViewGroup) findViewById(R.id.root));

        TextView text = (TextView) layout.findViewById(R.id.message);
        text.setText("커스텀 다이얼로그입니다.");
        ImageView icon = (ImageView) layout.findViewById(R.id.icon);
        icon.setImageResource(R.drawable.ic_launcher);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout);
        builder.setPositiveButton("확인", null);
        final AlertDialog dialog = builder.create();
*/
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }
}
