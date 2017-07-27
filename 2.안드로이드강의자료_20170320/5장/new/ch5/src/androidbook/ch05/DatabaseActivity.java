package androidbook.ch06;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DatabaseActivity extends ListActivity {
    private DBAdapter mDb;
    private ArrayList<Person> mPeople;
    private ArrayAdapter<Person> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        mDb = new DBAdapter(this);
        mPeople = mDb.getAllPeople();
        mAdapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, mPeople);
        setListAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        mDb.close();
        super.onDestroy();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Person p = mPeople.get(position);
        Toast.makeText(this, "delete " + p.getId(), Toast.LENGTH_SHORT);
        mDb.deletePerson(p.getId());
        refreshList();
    }

    public void onClickAddSamples(View v) {
        mDb.insertPerson("Kim", 20);
        mDb.insertPerson("Choi", 22);
        mDb.insertPerson("Lee", 23);
        refreshList();
    }

    private void refreshList() {
        mPeople.clear();
        mPeople.addAll(mDb.getAllPeople());
        mAdapter.notifyDataSetInvalidated();
    }
}
