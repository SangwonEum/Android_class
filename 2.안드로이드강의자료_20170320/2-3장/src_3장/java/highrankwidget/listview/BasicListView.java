package androidbook.ch03.highrankwidget.listview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidbook.ch03.R;

public class BasicListView extends Activity {


    ListView list;
    ArrayList<Integer> numbers;
    ArrayAdapter<Integer> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.basic_listview);

    list = (ListView)findViewById(R.id.list);
    numbers = new ArrayList<Integer>();

    for(int i=1;i<11;i++)
    {
        numbers.add(i);
    }

    adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1, numbers);
    list.setAdapter(adapter);

    }
}
