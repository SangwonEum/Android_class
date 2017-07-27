package com.ch1.ex2;

import java.util.ArrayList;

import com.example.androidprj.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
 
public class CustomListViewActivity extends Activity {
 
    // Data�� �������ִ� Adapter
    private CustomAdapter mCustomAdapter = null;
    // ���׸�(String)�� ����� ArrayList
    private ArrayList<String> mArrayList = new ArrayList<String>();
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list);
 
        setLayout();
 
        mCountBt.setText("���� üũ�� ���ڴ� = 0 �� �Դϴ�.");
 
        // ArrayList�� String���� �̷���� ������ Add �Ѵ�.
        mArrayList.add("ù��°");
        mArrayList.add("�ι�°");
        mArrayList.add("����°");
        mArrayList.add("�׹�°");
        mArrayList.add("�ټ���°");
        mArrayList.add("������°");
        mArrayList.add("�ϰ���°");
        mArrayList.add("������°");
 
        mCustomAdapter = new CustomAdapter(CustomListViewActivity.this , mArrayList);
        mListView.setAdapter(mCustomAdapter);
        mListView.setOnItemClickListener(mItemClickListener);
    }
 
    // ListView �ȿ� Item�� Ŭ���ÿ� ȣ��Ǵ� Listener
    private AdapterView.OnItemClickListener mItemClickListener = new
            AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1,
                int position, long arg3) {
            Toast.makeText(getApplicationContext(), ""+(position+1), 
                    Toast.LENGTH_SHORT).show();
 
            mCustomAdapter.setChecked(position);
            // Data ����� ȣ�� Adapter�� Data ���� ����� �˷��༭ Update ��.
            mCustomAdapter.notifyDataSetChanged();
 
        }
    };
 
 
    // Custom Adapter
    class CustomAdapter extends BaseAdapter {
 
        private ViewHolder viewHolder = null;
        // �並 ���� ����� ���� Inflater
        private LayoutInflater inflater = null;
        private ArrayList<String> sArrayList = new ArrayList<String>();
        private boolean[] isCheckedConfrim;
 
        public CustomAdapter (Context c , ArrayList<String> mList){
            inflater = LayoutInflater.from(c);
            this.sArrayList = mList;
            // ArrayList Size ��ŭ�� boolean �迭�� �����.
            // CheckBox�� true/false�� ���� �ϱ� ����
            this.isCheckedConfrim = new boolean[sArrayList.size()];
        }
 
        // CheckBox�� ��� �����ϴ� �޼���
        public void setAllChecked(boolean ischeked) {
            int tempSize = isCheckedConfrim.length;
            for(int a=0 ; a<tempSize ; a++){
                isCheckedConfrim[a] = ischeked;
            }
        }
 
        public void setChecked(int position) {
            isCheckedConfrim[position] = !isCheckedConfrim[position];
        }
 
        public ArrayList<Integer> getChecked(){
            int tempSize = isCheckedConfrim.length;
            ArrayList<Integer> mArrayList = new ArrayList<Integer>();
            for(int b=0 ; b<tempSize ; b++){
                if(isCheckedConfrim[b]){
                    mArrayList.add(b);
                }
            }
            return mArrayList;
        }
 
        @Override
        public int getCount() { 
            return sArrayList.size();
        }
 
        @Override
        public Object getItem(int arg0) {
            return null;
        }
 
        @Override
        public long getItemId(int arg0) {
            return 0;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	
            // ConvertView�� null �� ���
            View v = convertView;
 
            if( v == null ){
                viewHolder = new ViewHolder();
                // View�� inflater �����ش�.
                v = inflater.inflate(R.layout.row, null);
                viewHolder.cBox = (CheckBox) v.findViewById(R.id.main_check_box);
                v.setTag(viewHolder);
            }
 
            else {
                viewHolder = (ViewHolder)v.getTag();
            }
 
            // CheckBox�� �⺻������ �̺�Ʈ�� ������ �ֱ� ������ ListView�� ������
            // Ŭ������ʸ� ����ϱ� ���ؼ��� CheckBox�� �̺�Ʈ�� ���� �־�� �Ѵ�.
            viewHolder.cBox.setClickable(false);
            viewHolder.cBox.setFocusable(false);
 
            viewHolder.cBox.setText(sArrayList.get(position));
            // isCheckedConfrim �迭�� �ʱ�ȭ�� ��� false�� �ʱ�ȭ �Ǳ⶧����
            // �⺻������ false�� �ʱ�ȭ ��ų �� �ִ�.
            viewHolder.cBox.setChecked(isCheckedConfrim[position]);
 
            return v;
        }
    }
 
    class ViewHolder {
        // ���ο� Row�� �� CheckBox
        private CheckBox cBox = null;
    }
 
 
    private ListView mListView = null;
    private CheckBox mAllCheckBox = null;
    private Button mCountBt = null;
 
    /*
     * Layout
     */
    private void setLayout(){
        mListView = (ListView) findViewById(R.id.main_listview);
        mCountBt = (Button) findViewById(R.id.main_text_button);
        mCountBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountBt.setText("���� üũ�� ���ڴ� = "+
                        mCustomAdapter.getChecked().size()+" �� �Դϴ�.");
                // üũ�� �ִ� CheckBox�� Position�� ��� �´�.
                for(int i=0 ; i<mCustomAdapter.getChecked().size() ; i++){
                    Log.d("test", "üũ�� �ִ� Position = " + 
                            mCustomAdapter.getChecked().get(i));
                }
            }
        });
 
        mAllCheckBox = (CheckBox) findViewById(R.id.main_all_check_box);
        // ��ü üũ ��ư Ŭ���� Listener
        mAllCheckBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomAdapter.setAllChecked(mAllCheckBox.isChecked());
                // Adapter�� Data�� ��ȭ�� �������� Adapter�� �˷��ش�.
                mCustomAdapter.notifyDataSetChanged();
            }
        });
    }
}
 