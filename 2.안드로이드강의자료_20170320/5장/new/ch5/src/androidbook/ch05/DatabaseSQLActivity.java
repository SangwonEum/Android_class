package androidbook.ch06;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class DatabaseSQLActivity extends ListActivity {
    private DBSQLAdapter mDb;
    private ArrayList<CourseInfo> mPeople;
    private ArrayAdapter<CourseInfo> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_sql);

        mDb = new DBSQLAdapter(this);
        mDb.resetDatabase();
        mPeople = mDb.getAllStudentWithSubjects();
        mAdapter = new ArrayAdapter<CourseInfo>(this, android.R.layout.simple_list_item_1, mPeople);
        setListAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        mDb.close();
        super.onDestroy();
    }

    public void onClickTest(View v) {
        mDb.insertSampleData();
        refreshList();
    }

    private void refreshList() {
        mPeople.clear();
        mPeople.addAll(mDb.getAllStudentWithSubjects());
        mAdapter.notifyDataSetInvalidated();
    }
}
