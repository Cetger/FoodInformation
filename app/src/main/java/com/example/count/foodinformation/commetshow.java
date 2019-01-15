package com.example.count.foodinformation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class commetshow extends AppCompatActivity {
    private ArrayList<CommentList> comments;
    private ListView listView;
    private CommentAdapter commentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commetshow);

        listView=findViewById(R.id.listcomment);

        comments=new ArrayList<>();



        comments.add(new CommentList("ali","çok güzeldi",3.0f));
        comments.add(new CommentList("veli","çok güzeldiii",2.0f));
        comments.add(new CommentList("Deneme","Berbattı",0.0f));

        commentAdapter=new CommentAdapter(getApplicationContext(),comments);
        listView.setAdapter(commentAdapter);


    }
}
