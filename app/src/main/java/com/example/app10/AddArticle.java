package com.example.app10;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app10.sql.CRUD;
import com.example.app10.sql.Initialize;

import java.util.Calendar;

public class AddArticle extends AppCompatActivity {
    private final Calendar calendar = Calendar.getInstance();
    private int date[] = {calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)};
    private Initialize init =new Initialize(this,"article.db",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_essay);
        Button button1=findViewById(R.id.button1);
        Button button2=findViewById(R.id.button2);
        Button button3=findViewById(R.id.button3);
        EditText edit2=findViewById(R.id.edit2);
        EditText edit1=findViewById(R.id.edit1);
        EditText edit3=findViewById(R.id.edit3);

        edit2.setText(date[0] +"-"+ date[1] +"-"+ date[2]);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String dateTime = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
                        edit2.setText(dateTime);
                    }

                }, date[0], date[1], date[2]).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title= String.valueOf(edit1.getText());
                String date= String.valueOf(edit2.getText());
                String content=String.valueOf(edit3.getText());
                if(title.isEmpty()){
                    Toast.makeText(AddArticle.this,"请输入标题",Toast.LENGTH_SHORT).show();
                    return;
                }else if(content.isEmpty()){
                    Toast.makeText(AddArticle.this,"请输入内容",Toast.LENGTH_SHORT).show();
                    return;
                }
                new CRUD().addArticle(title,date,content,init.getWritableDatabase());
                Toast.makeText(AddArticle.this,"添加成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
