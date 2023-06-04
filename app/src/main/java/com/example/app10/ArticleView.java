package com.example.app10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app10.sql.Article;
import com.example.app10.sql.CRUD;
import com.example.app10.sql.Initialize;

import java.util.ArrayList;

public class ArticleView extends AppCompatActivity {
    private Initialize init =new Initialize(this,"article.db",null,1);
    ListView listView;
    Button button;
    TextView textView;
    String dataTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_article);
        listView=findViewById(R.id.view1);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.view2);
        Intent intent=getIntent();
        dataTime=intent.getStringExtra("dataTime");
        ArrayList<Article> article=new CRUD().selectByDate(dataTime,init.getWritableDatabase());

        flashView(article);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("act",article.get(i).getId());
                Intent intent1=new Intent(ArticleView.this, ArticleReader.class);
                intent1.putExtra("id",article.get(i).getId());
                startActivity(intent1);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Article> article=new CRUD().selectByDate(dataTime,init.getWritableDatabase());
        flashView(article);
    }

    private void flashView(ArrayList<Article> article){

        String[] data=query(article);
        setView2Text(dataTime,data.length);

        ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
        listView.setAdapter(arrayAdapter);
    }

    private String[] query(ArrayList<Article> article){
        String[] data = new String[article.size()];
        int i=0;
        for(Article article1:article){
            data[i]=article1.getTitle();
            Log.d("TAG", article1.getTitle());
            i++;
        }
        return data;
    }

    private void setView2Text(String dataTime,int size){
        String text;
        if(size==0){
            text=dataTime+"没有文章";
        }else{
            text=dataTime+"有"+size+"篇文章";
        }
        textView.setText(text);
    }
}
