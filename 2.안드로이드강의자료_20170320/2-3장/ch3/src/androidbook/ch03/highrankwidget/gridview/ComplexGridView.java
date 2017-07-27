package androidbook.ch03.highrankwidget.gridview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidbook.ch03.R;
import androidbook.ch03.highrankwidget.contactsbook.GridViewContactsBook;

public class ComplexGridView extends Activity {


    GridView gridview;
    ArrayList<GridViewContactsBook> contacts;
    ContactsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.complex_gridview);
		gridview = (GridView)findViewById(R.id.gridview);
		contacts = new ArrayList<GridViewContactsBook >(); 
		contacts.add(new GridViewContactsBook ("내 사랑 아이유", "010-0000-0000"));
		contacts.add(new GridViewContactsBook ("애프터스쿨", "010-1111-1111"));
		contacts.add(new GridViewContactsBook ("소녀시대", "010-2222-2222"));
		contacts.add(new GridViewContactsBook ("미스에이", "010-3333-3333"));
		contacts.add(new GridViewContactsBook ("시크릿", "010-4444-4444"));
		contacts.add(new GridViewContactsBook ("포미닛", "010-5555-5555"));
		contacts.add(new GridViewContactsBook ("에프엑스", "010-6666-6666"));
		contacts.add(new GridViewContactsBook ("티아라", "010-7777-7777"));
		contacts.add(new GridViewContactsBook ("카라", "010-8888-8888"));
		adapter = new ContactsAdapter(contacts);
		gridview.setAdapter(adapter);
    }
    class ContactsAdapter extends BaseAdapter 
    {
    private ArrayList<GridViewContactsBook> object;
    public ContactsAdapter(ArrayList<GridViewContactsBook> object) {
      super();
      this.object = object;      
    }
    public int getCount() { 
      return object.size();
    }
    public Object getItem(int position) {      
      return null;
    }
    public long getItemId(int position) {      
      return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) 
    {
      ViewHolder holder;
      if(convertView == null)
      {
        LayoutInflater inflater = LayoutInflater.from(ComplexGridView.this); 
        convertView = inflater.inflate(R.layout.gridview_listrow,parent,false);
        holder = new ViewHolder();
        holder.txtName = (TextView)convertView.findViewById(R.id.txt_name);
        holder.txtNumber = (TextView)convertView.findViewById(R.id.txt_number);
        convertView.setTag(holder);
      }
      else
      {
        holder = (ViewHolder)convertView.getTag();
      }
      String name = object.get(position).getName(); 
      String phonenumber = object.get(position).getPhoneNumber();
      holder.txtName.setText(name); 
      holder.txtNumber.setText(phonenumber);

      return convertView;
    }

  }
  static class ViewHolder 
  {
    TextView txtName;
    TextView txtNumber;
} 
}
