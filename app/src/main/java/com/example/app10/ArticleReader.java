package com.example.app10;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app10.sql.Article;
import com.example.app10.sql.CRUD;
import com.example.app10.sql.Initialize;

import java.util.ArrayList;

public class ArticleReader extends AppCompatActivity {
    private Initialize init =new Initialize(this,"article.db",null,1);
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_reader);
        Intent intent=getIntent();
        String id= intent.getStringExtra("id");
        EditText editText=findViewById(R.id.edit1);
        EditText editText2=findViewById(R.id.edit2);
        TextView textView=findViewById(R.id.view);
        Button button=findViewById(R.id.button1);
        Button button2=findViewById(R.id.button2);

        Article article=new CRUD().selectById(id,init.getWritableDatabase());

        editText.setText(article.getTitle());
        editText2.setText(article.getDate());
        textView.setText(article.getContent());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ArticleReader.this);
                builder.setTitle("提示");
                builder.setMessage("是否删除？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new CRUD().delete(id,init.getWritableDatabase());
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    };

                });
                builder.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
