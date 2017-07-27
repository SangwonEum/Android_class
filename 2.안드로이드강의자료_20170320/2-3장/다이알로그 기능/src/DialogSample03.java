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
        dialog.setTitle("����");
        
        TextView text = (TextView) dialog.findViewById(R.id.message);
        text.setText("Ŀ���� ���̾�α��Դϴ�.");
        ImageView icon = (ImageView) dialog.findViewById(R.id.icon);
        icon.setImageResource(R.drawable.ic_launcher);


        //Ŀ���� ���̾˷α׿� ��ư�� �߰��Ѵ�.
        /*View layout = getLayoutInflater().inflate(R.layout.custom_dialog, (ViewGroup) findViewById(R.id.root));

        TextView text = (TextView) layout.findViewById(R.id.message);
        text.setText("Ŀ���� ���̾�α��Դϴ�.");
        ImageView icon = (ImageView) layout.findViewById(R.id.icon);
        icon.setImageResource(R.drawable.ic_launcher);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout);
        builder.setPositiveButton("Ȯ��", null);
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
