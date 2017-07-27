package androidbook.ch03.highrankwidget.listview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidbook.ch03.R;
import androidbook.ch03.highrankwidget.contactsbook.ListViewContactsBook;

public class ComplexListView extends Activity {


    ListView list;
    ArrayList<ListViewContactsBook> contacts;
    ContactsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.complex_listview);
		list = (ListView)findViewById(R.id.list);
		contacts = new ArrayList<ListViewContactsBook >(); 
		contacts.add(new ListViewContactsBook ("내 사랑 아이유", "010-0000-0000"));
		contacts.add(new ListViewContactsBook ("애프터스쿨", "010-1111-1111"));
		contacts.add(new ListViewContactsBook ("소녀시대", "010-2222-2222"));
		contacts.add(new ListViewContactsBook ("카라", "010-3333-3333"));    
		adapter = new ContactsAdapter(contacts);
			list.setAdapter(adapter);
    }
    class ContactsAdapter extends BaseAdapter 
    {
    private ArrayList<ListViewContactsBook> object;
    public ContactsAdapter(ArrayList<ListViewContactsBook> object) {
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
        LayoutInflater inflater = LayoutInflater.from(ComplexListView.this); 
        convertView = inflater.inflate(R.layout.listview_listrow,parent,false);
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
