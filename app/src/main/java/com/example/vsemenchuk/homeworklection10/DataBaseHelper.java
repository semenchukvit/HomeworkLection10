package com.example.vsemenchuk.homeworklection10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MyDB.db";
    private static final int DB_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Group.TABLE_GROUPS + "(" +
                Group.KEY_ID + " integer primary key autoincrement," +
                Group.KEY_NAME + " text" +
                ");"
        );

        db.execSQL("create table " + Student.TABLE_STUDENTS + "(" +
                Student.KEY_ID + " integer primary key autoincrement," +
                Student.KEY_FIRST_NAME + " text," +
                Student.KEY_LAST_NAME + " text," +
                Student.KEY_AGE + " integer," +
                Student.KEY_GROUP_ID + " integer" +
                ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertGroup(Group group) {
        long id = 0;
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues cv = new ContentValues();
            cv.put(Group.KEY_NAME, group.getName());
            id = db.insert(Group.TABLE_GROUPS, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public long insertStudent(Student student, Group group) {
        long id = 0;
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues cv = new ContentValues();
            cv.put(Student.KEY_FIRST_NAME, student.getFirstName());
            cv.put(Student.KEY_LAST_NAME, student.getLastName());
            cv.put(Student.KEY_AGE, student.getAge());
            cv.put(Student.KEY_GROUP_ID, group.getId());
            id = db.insert(Student.TABLE_STUDENTS, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public ArrayList<Group> getGroups() {
        ArrayList<Group> groups = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(Group.TABLE_GROUPS, null, null, null,
                    null, null, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Group group = new Group();
                    group.setId((int) cursor.getLong(cursor.getColumnIndex(Group.KEY_ID)));
                    group.setName(cursor.getString(cursor.getColumnIndex(Group.KEY_NAME)));

                    groups.add(group);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return groups;
    }

    public ArrayList<Student> getStudents(Group group) {
        ArrayList<Student> students = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;

        try {
            String id = String.valueOf(group.getId());
            cursor = db.query(Student.TABLE_STUDENTS, null, "group_id = ?",
                    new String[]{id}, null, null, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Student student = new Student();
                    student.setId((int) cursor.getLong(cursor.getColumnIndex(Student.KEY_ID)));
                    student.setFirstName(cursor.getString(cursor.getColumnIndex(Student.KEY_FIRST_NAME)));
                    student.setLastName(cursor.getString(cursor.getColumnIndex(Student.KEY_LAST_NAME)));
                    student.setAge((int) cursor.getLong(cursor.getColumnIndex(Student.KEY_AGE)));

                    students.add(student);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return students;
    }
}
