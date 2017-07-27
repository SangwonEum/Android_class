package androidbook.ch03.widget.imageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import androidbook.ch03.R;

public class ComplexImageView extends Activity implements OnClickListener{
	ImageView image;    
    Button btnChange;
    Button btnAlpha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.complex_imageview);
    image = (ImageView)findViewById(R.id.image);
    btnChange = (Button)findViewById(R.id.btn_change);
    btnAlpha = (Button)findViewById(R.id.btn_alpha);        
    btnChange.setOnClickListener(this);
    btnAlpha.setOnClickListener(this);

    }

    public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId())
	{
	case R.id.btn_change :
	image.setImageResource(R.drawable.froyo);
	    break;
	case R.id.btn_alpha :
	image.setAlpha(50);
	    break;
	}

    }

}

