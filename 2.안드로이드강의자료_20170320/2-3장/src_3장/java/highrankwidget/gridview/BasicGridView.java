package androidbook.ch03.highrankwidget.gridview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import androidbook.ch03.R;

public class BasicGridView extends Activity {

    GridView gridview;
    GridViewAdapter adapter;
    int[] gridImage = {R.drawable.gallery1,
            R.drawable.gallery2,
            R.drawable.gallery3,
            R.drawable.gallery4,
            R.drawable.gallery5,
            R.drawable.gallery6,
            R.drawable.gallery7,
            R.drawable.gallery8};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_gridview);
        gridview = (GridView)findViewById(R.id.gridview);

        adapter = new GridViewAdapter(this,gridImage);
        gridview.setAdapter(adapter);
    }
    	class GridViewAdapter extends BaseAdapter
    {
        private int[] object;
        private Context context;

        public GridViewAdapter() {
            super();
        }

        public GridViewAdapter(Context context,int[] object) {
            super();
            this.context = context;
            this.object = object;
        }
        public int getCount() {
            return object.length;
        }
        public Object getItem(int arg0) {
            return null;
        }
        public long getItemId(int arg0) {
            return 0;
        }
        //스크롤 시 현재 보이기 시작하는 위치를 자동으로 알아서 그 위치에 해당하는 이미지를 리스트에 추가한다.
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView image = new ImageView(context);
            image.setImageResource(object[position]);
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            image.setLayoutParams(new GridView.LayoutParams(70,70));
            return image;
        }
    }
}
