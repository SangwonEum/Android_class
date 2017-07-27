package androidbook.ch03.highrankwidget.gallery;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;
import androidbook.ch03.R;
import androidbook.ch03.highrankwidget.contactsbook.GalleryContactsBook;

public class ComplexGallery extends Activity {


    Gallery gallery;
    ArrayList<GalleryContactsBook> contacts;
    ContactsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.complex_gallery);
		gallery = (Gallery)findViewById(R.id.gallery);
		contacts = new ArrayList<GalleryContactsBook >(); 
		contacts.add(new GalleryContactsBook ("내 사랑 아이유", "010-0000-0000","IU@gmail.com"));
		contacts.add(new GalleryContactsBook ("애프터스쿨", "010-1111-1111","AfterSchool@gmail.com"));
		contacts.add(new GalleryContactsBook ("소녀시대", "010-2222-2222","Girlgeneration@gmail.com"));
		contacts.add(new GalleryContactsBook ("카라", "010-3333-3333","TARA@gmail.com"));    
		adapter = new ContactsAdapter(contacts);
		gallery.setAdapter(adapter);
    }
    class ContactsAdapter extends BaseAdapter 
    {
    private ArrayList<GalleryContactsBook> object;
    public ContactsAdapter(ArrayList<GalleryContactsBook> object) {
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
        LayoutInflater inflater = LayoutInflater.from(ComplexGallery.this); 
        convertView = inflater.inflate(R.layout.gallery_listrow,parent,false);
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
