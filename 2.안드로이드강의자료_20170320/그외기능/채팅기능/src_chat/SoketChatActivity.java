package com.example.atest.chat;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.atest.R;

public class SoketChatActivity extends Activity implements OnClickListener {
    private final static String BR=
        System.getProperty("line.separator");
    private final static String IP=
        "192.168.0.100";
    
    private SoketChatActivity current;   
    private TextView lblReceive;
    private EditText edtSend;   
    private Button   btnSend;   
    
    private Socket       socket; 
    private InputStream  in;     
    private OutputStream out;   
    
    private final Handler handler=new Handler();
    private String txtReceive; 
    
    private String id=null;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        current=this;
        
       
        RelativeLayout layout=new RelativeLayout(this);
        layout.setBackgroundColor(Color.rgb(255,255,255));
        setContentView(layout);  
        
        
        lblReceive=new TextView(this);
        lblReceive.setId(1);
        lblReceive.setText("");
        lblReceive.setTextSize(16.0f);                  
        lblReceive.setTextColor(Color.rgb(0,0,0));
        RelativeLayout.LayoutParams param1=
            new RelativeLayout.LayoutParams(320,400);
        lblReceive.setLayoutParams(param1);
        layout.addView(lblReceive);

        
        edtSend=new EditText(this);
        edtSend.setId(2);
        edtSend.setText("",TextView.BufferType.NORMAL);
        RelativeLayout.LayoutParams param2=
            new RelativeLayout.LayoutParams(200,50);
        param2.addRule(RelativeLayout.BELOW,1);
        edtSend.setLayoutParams(param2);
        layout.addView(edtSend);        
        
        
        btnSend=new Button(this);
        btnSend.setText("send message");
        btnSend.setOnClickListener(this);
        RelativeLayout.LayoutParams param3=
            new RelativeLayout.LayoutParams(200,50);
        param3.addRule(RelativeLayout.BELOW,1);
        param3.addRule(RelativeLayout.RIGHT_OF,2);
        btnSend.setLayoutParams(param3);
        layout.addView(btnSend);
        
      final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_id);
        dialog.setTitle("제목");
     
        final  EditText eText = (EditText) dialog.findViewById(R.id.editId);
        dialog.show();
        
        Button btn_conn=(Button) dialog.findViewById(R.id.btn_conn);
        Button btn_cancel=(Button) dialog.findViewById(R.id.btn_cancel);
        
        btn_conn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				id=eText.getText().toString();
				Log.d("TAG","id : "+id);
				
				 Thread cThread =new Thread(){public void run() {
		            try {
		                connect(IP,6001,id);
		            } catch (Exception e) {
		            }
		        }}    ; 
		        cThread.start();
				dialog.dismiss();
			}
        	
        });
        
        btn_cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
        	
        });
//      
//        Thread cThread =new Thread(){public void run() {
//            try {
//                connect(IP,6001);
//            } catch (Exception e) {
//            }
//        }}    ; 
//        cThread.start(); 


	}
	
	//메시지 수신시 화면에 출력한다.
    private void connect(String ip,int port,String id) {
        int size;
        byte[] w =new byte[10240];        
        txtReceive="";
        try {
           
            socket=new Socket(ip,port);
            in =socket.getInputStream();
            out=socket.getOutputStream();
            
         
            while (socket!=null && socket.isConnected()) {
              
                size=in.read(w);
                if (size<=0) continue;
                txtReceive=new String(w,0,size,"UTF-8");

              
                handler.post(new Runnable(){
                    public void run() {
                       
                        lblReceive.setText(
                            lblReceive.getText()+txtReceive+BR);
                    }
                });
            }
        } catch (Exception e) {
            handler.post(new Runnable(){
                public void run() {
                	SoketChatActivity.showDialog(current,"","connecting");
                }
            });
        }
    }    
  
    //버튼 클릭시 서버로 메시지를 전송한다.
    public void onClick(View v) {
        if (v==btnSend) {
            try {
                
                if (socket!=null && socket.isConnected()) {
                    PrintWriter out = new PrintWriter(new BufferedWriter(
    						new OutputStreamWriter(socket.getOutputStream())), true);
    				out.println(id+" : " +edtSend.getText().toString());
                    out.flush();
                    edtSend.setText("",TextView.BufferType.NORMAL);
                }
            } catch (Exception e) {
                handler.post(new Runnable(){
                    public void run() {
                    	SoketChatActivity.showDialog(current,"","not connected");
                    }
                });
            }           
        }
    }    
    
    public static void showDialog(final Activity activity,String title,String text) {
        AlertDialog.Builder ad=new AlertDialog.Builder(activity);
        ad.setTitle(title);
        ad.setMessage(text);
        ad.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int whichButton) {
                activity.setResult(Activity.RESULT_OK);
            }
        });
        ad.create();
        ad.show();
    }
}
