package androidbook.ch03.highrankwidget.gallery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import androidbook.ch03.R;

public class BasicGallery extends Activity {
TextView text;
    Gallery gallery;
    GalleryAdapter adapter;
    private int[] galleryImage={R.drawable.gallery1,
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
        setContentView(R.layout.basic_gallery);
        text = (TextView)findViewById(R.id.text);
        gallery = (Gallery)findViewById(R.id.gallery);  //레이아웃에서 갤러리를 찾는다.
        adapter = new GalleryAdapter(this, galleryImage);  //갤러리 어답터를 생성한다.
        gallery.setAdapter(adapter);                       //갤러리에 어답터를 연결한다.
        //갤러리에서 지원하는 이벤트
    	gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) 
            { 
                text.setText(String.valueOf(arg2)+"번째 이미지입니다.");                
            }

            public void onNothingSelected(AdapterView<?> arg0) {}
        });
    }

    class GalleryAdapter extends BaseAdapter
    {
        private Context context;
        private int[] Object;

        public GalleryAdapter(Context context, int[] galleryImage) {
            super();
            this.context = context;
            this.Object = galleryImage;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return Object.length;
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

      //스크롤 시 현재 보이기 시작하는 위치를 자동으로 알아서 그 위치에 해당하는 이미지를 리스트에 추가한다.
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ImageView image = new ImageView(context);
            image.setImageResource(Object[position]);
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            image.setLayoutParams(new Gallery.LayoutParams(100,100));
            return image;
        }        
    }
}
