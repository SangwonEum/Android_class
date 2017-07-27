package androidbook.ch03.widget;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidbook.ch03.R;
import androidbook.ch03.widget.button.ButtonActivity;
import androidbook.ch03.widget.checkbox.CheckBoxActivity;
import androidbook.ch03.widget.edittext.EditTextActivity;
import androidbook.ch03.widget.imageview.ImageViewActivity;
import androidbook.ch03.widget.nameapplication.NameApplication;
import androidbook.ch03.widget.pizza.PizzaActivity;
import androidbook.ch03.widget.radiobutton.RadioButtonActivity;
import androidbook.ch03.widget.textview.TextViewActivity;

public class WidgetActivity extends Activity implements OnItemClickListener{
	private ListView list;
	private ArrayList<String> activity;
	private ArrayAdapter<String> adapter;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widgetactivity);
		list = (ListView)findViewById(R.id.list);
        activity = new ArrayList<String>();
        activity.add("TextView");
        activity.add("EditText");
        activity.add("Button");
        activity.add("이름 애플리케이션");
        activity.add("ImageView");
        activity.add("RadioButton");
        activity.add("CheckBox");
        activity.add("피자주문 애플리케이션");
        
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activity);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
	}
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		// TODO Auto-generated method stub
		switch(position)
		{
		case 0 :
			intent = new Intent(WidgetActivity.this,TextViewActivity.class);
			startActivity(intent);
			break;
		case 1 :
			intent = new Intent(WidgetActivity.this,EditTextActivity.class);
			startActivity(intent);
			break;
		case 2 :
			intent = new Intent(WidgetActivity.this,ButtonActivity.class);
			startActivity(intent);
			break;
		case 3 :
			intent = new Intent(WidgetActivity.this,NameApplication.class);
			startActivity(intent);
			break;
		case 4 :
			intent = new Intent(WidgetActivity.this,ImageViewActivity.class);
			startActivity(intent);
			break;
		case 5 :
			intent = new Intent(WidgetActivity.this,RadioButtonActivity.class);
			startActivity(intent);
			break;
		case 6 :
			intent = new Intent(WidgetActivity.this,CheckBoxActivity.class);
			startActivity(intent);
			break;
		case 7 :
			intent = new Intent(WidgetActivity.this,PizzaActivity.class);
			startActivity(intent);
			break;
		}
	}
}
