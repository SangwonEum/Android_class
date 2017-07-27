package com.android.calculate;

//import com.androidbook.views.R;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;



public class Calculator extends Activity {
    /** Called when the activity is first created. */
	private  static boolean afterOperator = false;
	private String a;
	private String b ;
	private TextView textView;
	private String operator = null;
	


	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        

    	final Button button0 = (Button) findViewById(R.id.Button0);
    	final Button button1 = (Button) findViewById(R.id.Button1);
    	final Button button2 = (Button) findViewById(R.id.Button2);
    	final Button button3 = (Button) findViewById(R.id.Button3);
    	final Button button4 = (Button) findViewById(R.id.Button4);
    	final Button button5 = (Button) findViewById(R.id.Button5);
    	final Button button6 = (Button) findViewById(R.id.Button6);
    	final Button button7 = (Button) findViewById(R.id.Button7);
    	final Button button8 = (Button) findViewById(R.id.Button8);
    	final Button button9 = (Button) findViewById(R.id.Button9);
    	
    	final Button buttonPlus = (Button) findViewById(R.id.ButtonPlus);
    	final Button buttonMinus = (Button) findViewById(R.id.ButtonMinus);
    	final Button buttonDiv = (Button) findViewById(R.id.ButtonDivide);
    	final Button buttonProduct = (Button) findViewById(R.id.ButtonProduct);
    	final Button buttonCL = (Button) findViewById(R.id.ButtonCL);
    	final Button buttonEqual = (Button) findViewById(R.id.ButtonEqual);
    	

        
        //final Button button =new Button();
        textView = (TextView)findViewById(R.id.EditText01);
        
    
	
	button0.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
		 if(afterOperator ==false){
				if(a==null){
					a="0";
				}else{
					a =a+"0";
				}
				 textView.setText( a);
								
		}else{
			if(b==null){
				b="0";
			}else{
				b =b+"0";
			}
			 textView.setText( b);
			
		}
	  }
		   
	});
  
	
	button1.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {			
			 if(afterOperator ==false){
					if(a==null){
						a="1";
					}else{
						a =a+"1";
					}
					 textView.setText( a);
									
			}else{
				if(b==null){
					b="1";
				}else{
					b =b+"1";
				}
				 textView.setText( b);
				
			}
		}	   
	});
	
	button2.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			 if(afterOperator ==false){
					if(a==null){
						a="2";
					}else{
						a =a+"2";
					}
					 textView.setText( a);
									
			}else{
				if(b==null){
					b="2";
				}else{
					b =b+"2";
				}
				 textView.setText( b);
				
			}
			}	   
	});
	
	button3.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			 if(afterOperator ==false){
					if(a==null){
						a="3";
					}else{
						a =a+"3";
					}
					 textView.setText( a);
									
			}else{
				if(b==null){
					b="3";
				}else{
					b =b+"3";
				}
				 textView.setText( b);
				
			}
			}	   
	});
	
	button4.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			 if(afterOperator ==false){
					if(a==null){
						a="4";
					}else{
						a =a+"4";
					}
					 textView.setText( a);
									
			}else{
				if(b==null){
					b="4";
				}else{
					b =b+"4";
				}
				 textView.setText( b);
				
			}
			}	   
	});
	
	button5.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			 if(afterOperator ==false){
					if(a==null){
						a="5";
					}else{
						a =a+"5";
					}
					 textView.setText( a);
									
			}else{
				if(b==null){
					b="5";
				}else{
					b =b+"5";
				}
				 textView.setText( b);
				
			}
			}	   
	});
	
	button6.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			 if(afterOperator ==false){
					if(a==null){
						a="6";
					}else{
						a =a+"6";
					}
					 textView.setText( a);
									
			}else{
				if(b==null){
					b="6";
				}else{
					b =b+"6";
				}
				 textView.setText( b);
				
			}
			}	   
	});
	
	
	button7.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			 if(afterOperator ==false){
					if(a==null){
						a="7";
					}else{
						a =a+"7";
					}
					 textView.setText( a);
									
			}else{
				if(b==null){
					b="7";
				}else{
					b =b+"7";
				}
				 textView.setText( b);
				
			}
			}	   
	});
	
	button8.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			 if(afterOperator ==false){
					if(a==null){
						a="8";
					}else{
						a =a+"8";
					}
					 textView.setText( a);
									
			}else{
				if(b==null){
					b="8";
				}else{
					b =b+"8";
				}
				 textView.setText( b);
				
			}
			}	   
	});
	
	button9.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			 if(afterOperator ==false){
					if(a==null){
						a="9";
					}else{
						a =a+"9";
					}
					 textView.setText( a);
									
			}else{
				if(b==null){
					b="9";
				}else{
					b =b+"9";
				}
				 textView.setText( b);
				
			}
			}	   
	});

	buttonPlus.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			if(a !=null){
				operator ="+";
				afterOperator=true;
			}
		}	   
	});	
	
	buttonMinus.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			if(a != null){
			operator="-";
			afterOperator=true;			
			}
		}
	});	
	
	
	buttonDiv.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			if(a !=null){
			operator="/";
			afterOperator=true;			
			}	   
		}	
	});
	
	buttonProduct.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			if(a != null){
			operator="*";
			afterOperator=true;			
			}	   
		}
	});
	
	buttonCL.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			afterOperator=false;
		//√ ±‚»≠ Ω√≈¥
			a = null;
			b = null;
			textView.setText( "0");
			}	   
	});	
	
	buttonEqual.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			 int result = 0;
			if(afterOperator==true && a!=null && b != null){
				if(operator=="+"){
					result = Integer.parseInt(a)+Integer.parseInt(b);
				
				}else if(operator=="-"){
					result = Integer.parseInt(a)-Integer.parseInt(b);
				}else if(operator=="*"){
					result = Integer.parseInt(a)* Integer.parseInt(b);
				}else if(operator=="/"){
					result = Integer.parseInt(a)/Integer.parseInt(b);
				}
			   
			}
			 textView.setText( Integer.toString(result));
			 
				afterOperator=false;
				//√ ±‚»≠ Ω√≈¥
					a = null;
					b = null;
					

			
			}	   
	});	
	
   }
    
}