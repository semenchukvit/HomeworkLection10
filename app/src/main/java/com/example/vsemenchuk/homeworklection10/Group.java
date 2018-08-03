package com.example.vsemenchuk.homeworklection10;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Group implements Parcelable{

    public static final String TABLE_GROUPS = "groups";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";

    private int id;
    private String name;
    private ArrayList<Student> students;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeTypedList(this.students);
    }

    protected Group(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.students = in.createTypedArrayList(Student.CREATOR);
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel source) {
            return new Group(source);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}
