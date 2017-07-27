package test.com.seoulapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CultureAdapter  extends BaseAdapter{
	Context context;
	int layoutId;
	ArrayList<CultureVo> mArrayList;
	LayoutInflater inflater;
	
	public CultureAdapter(Context context,int layoutId,ArrayList<CultureVo> mArrayList){
		this.context=context;
		this.layoutId=layoutId;
		this.mArrayList=mArrayList;
		inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	@Override
	public int getCount() {
		return mArrayList.size();
	}

	@Override
	public String getItem(int position) {
		return mArrayList.get(position).getMagage_Num();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=inflater.inflate(layoutId,parent,false);
			TextView tNum=(TextView)convertView.findViewById(R.id.tNum);
			tNum.setText(mArrayList.get(position).getMagage_Num());
			
			TextView tName=(TextView)convertView.findViewById(R.id.tName);
			tName.setText(mArrayList.get(position).getName_Kor());
			
		}
			
		return convertView;
	}
	

}
