package androidbook.ch06;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSQLAdapter extends SQLiteOpenHelper {
    private static final String DB_NAME = "test_sql.db";
    private static final int VERSION = 2;
    private static final String TABLE_STUDENT = "student";
    private static final String TABLE_SUBJECTS = "subjects";

    private static final String CREATE_TABLE_STUDENT =
        "CREATE TABLE student (name text not null)";

    private static final String CREATE_TABLE_SUBJECTS =
        "CREATE TABLE subjects (" +
        "student_id integer, " +
        "subject_name text not null, " +
        "FOREIGN KEY(student_id) REFERENCES student(rowid)" +
        ")";

    private SQLiteDatabase db;

    public DBSQLAdapter(Context context) {
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
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_SUBJECTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);
        onCreate(db);
    }

    public void resetDatabase() {
        db.execSQL("DELETE FROM subjects");
        db.execSQL("DELETE FROM student");
    }

    public long insertStudent(String name) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        return db.insert(TABLE_STUDENT, null, cv);
    }

    public void insertSubject(long studentId, String subjectName) {
        db.execSQL("INSERT INTO subjects(student_id, subject_name) VALUES(?, ?)",
            new Object[] { studentId, subjectName });
    }

    public void insertSampleData() {
        long id1 = insertStudent("Kim");
        insertSubject(id1, "Math");
        insertSubject(id1, "English");

        long id2 = insertStudent("Lee");
        insertSubject(id2, "English");
        insertSubject(id2, "Korean");
    }

    public ArrayList<CourseInfo> getAllStudentWithSubjects() {
        ArrayList<CourseInfo> infos = new ArrayList<CourseInfo>();

        String sql = "SELECT * FROM student, subjects " +
                     "WHERE student.rowid = subjects.student_id ";

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            final int indexName = c.getColumnIndex("name");
            final int indexSubject = c.getColumnIndex("subject_name");

            do {
                String name = c.getString(indexName);
                String subject = c.getString(indexSubject);
                infos.add(new CourseInfo(name, subject));
            } while (c.moveToNext());
        }
        c.close();

        return infos;
    }
}
