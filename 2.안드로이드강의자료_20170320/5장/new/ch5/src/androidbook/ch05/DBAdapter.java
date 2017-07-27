package androidbook.ch06;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper {
    private static final String DB_NAME = "test.db";  //데이터 베이스 이름
    private static final int VERSION = 1;
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String AGE = "age";

    private static final String TABLE_NAME = "people";
    private static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + " (" +
        ID + " integer primary key autoincrement, " +
        NAME + " text not null, " +
        AGE + " integer default 0 )";

    private SQLiteDatabase db;

    //생성자를 호출하여 데이터베이스를 생성한다.
    public DBAdapter(Context context) {
        super(context, DB_NAME, null, VERSION);  
        db = this.getWritableDatabase();
    }

    @Override
    public synchronized void close() {
        db.close();
        super.close();
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);  //데이터베이스에 테이블을 생성한다.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // 데이터베이스에 이름과 나이를 저장한다.
    public boolean insertPerson(String name, int age) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(AGE, age);
        return db.insert(TABLE_NAME, null, cv) != -1;
    }

    //  데이터베이스에 저장된 레코드를 가지고온다.
    public ArrayList<Person> getAllPeople() {
        ArrayList<Person> people = new ArrayList<Person>();
        Cursor c = db.query(TABLE_NAME, new String[] {ID, NAME, AGE}, null, null, null, null, ID + " ASC");

        if (c.moveToFirst()) {
            final int indexId = c.getColumnIndex(ID);
            final int indexName = c.getColumnIndex(NAME);
            final int indexAge = c.getColumnIndex(AGE);

            do {
                int id = c.getInt(indexId);
                String name = c.getString(indexName);
                int age = c.getInt(indexAge);
                people.add(new Person(id, name, age));
            } while (c.moveToNext());
        }
        c.close();

        return people;
    }

    //  데이터를 업데이트한다.
    public boolean updatePerson(Person p) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, p.getName());
        cv.put(AGE, p.getAge());
        String[] params = new String[] { Integer.toString(p.getId()) };
        int result = db.update(TABLE_NAME, cv, ID + "=?", params);
        return result > 0;
    }

    // 레코드를 삭제한다.
    public boolean deletePerson(int id) {
        String[] params = new String[] { Integer.toString(id) };
        int result = db.delete(TABLE_NAME, ID + "=?", params);
        return result > 0;
    }
}
