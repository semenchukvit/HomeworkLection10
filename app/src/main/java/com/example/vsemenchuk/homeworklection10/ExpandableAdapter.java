package com.example.vsemenchuk.homeworklection10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater;
    private int groupResource;
    private int itemResource;
    private ArrayList<Group> groups;

    public ExpandableAdapter(Context context, int groupResourceId, int itemResourceId, ArrayList<Group> groups) {
        inflater = LayoutInflater.from(context);
        this.groupResource = groupResourceId;
        this.itemResource = itemResourceId;
        this.groups = groups;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getStudents().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getStudents().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(groupResource, null);

        Group group = (Group) getGroup(groupPosition);
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(group.getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(itemResource, null);

        Student student = (Student) getChild(groupPosition, childPosition);
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(student.toString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
