package com.example.count.foodinformation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CommentAdapter extends ArrayAdapter<CommentList> {
    private final Context context;
    private RecyclerView.ViewHolder holder;
    private final ArrayList<CommentList> comments;


    public CommentAdapter(Context context, ArrayList<CommentList> comments) {

        super(context, R.layout.list_view_item, comments);
        this.context = context;
        this.comments = comments;

    }
    private static class ViewHolder {
        TextView commentyazar;
        TextView comment;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;

        if (convertView == null) {
            holder= new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_view_item,parent, false);
            holder.commentyazar = (TextView) convertView.findViewById(R.id.yazarid);
            holder.comment = (TextView) convertView.findViewById(R.id.yorumid);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CommentList comment = comments.get(position);
        if (comment != null) {
            holder.commentyazar.setText(comment.getYazarid());
            holder.comment.setText(comment.getYorum());
        }
        return convertView;
    }

}
