package com.example.administrator.myswip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.streamlist.Contant;
import com.example.streamlist.StreamList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private StreamList streamList;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        list.add("郑州");
        list.add("开封");
        list.add("洛阳");
        list.add("驻马店");
        list.add("信仰");
        streamList = (StreamList) findViewById(R.id.sl);
        streamList.setType(Contant.TEXT_DELETE);
        streamList.setData(list);
    }

    public void show(View view) {
        String s = "sfggsaggggs";
        Random random = new Random();
        int index = random.nextInt(s.length() - 2) + 1;
        streamList.add(s.substring(0, index));
    }
}
