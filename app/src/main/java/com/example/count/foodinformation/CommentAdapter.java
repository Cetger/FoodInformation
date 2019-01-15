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

    private final LayoutInflater inflater;
    private final Context context;
    private RecyclerView.ViewHolder holder;
    private final ArrayList<CommentList> comments;


    public CommentAdapter(Context context, ArrayList<CommentList> comments) {

        super(context, 0, comments);
        this.context = context;
        this.comments = comments;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public CommentList getItem(int position) {

        return comments.get(position);

    }

    @Override
    public long getItemId(int position) {
        return comments.get(position).hashCode();

    }

    private static class ViewHolder {
        TextView commentyazar;
        TextView comment;
        RatingBar ratingvote;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.list_view_item, null);

            holder.commentyazar = (TextView) convertView.findViewById(R.id.yazarid);
            holder.comment = (TextView) convertView.findViewById(R.id.yorumid);
            holder.ratingvote = (RatingBar) convertView.findViewById(R.id.starid);
            convertView.setTag(holder);

        } else {
            //Get viewholder we already created
            holder = (ViewHolder) convertView.getTag();
        }

        CommentList comment = comments.get(position);
        if (comment != null) {
            holder.commentyazar.setText(comment.getYazarid());
            holder.comment.setText(comment.getYorum());
            holder.ratingvote.setRating(comment.getOy());
        }
        return convertView;
    }

}
