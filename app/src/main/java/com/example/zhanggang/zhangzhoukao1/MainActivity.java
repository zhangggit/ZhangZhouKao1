package com.example.zhanggang.zhangzhoukao1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (MyView) findViewById(R.id.myview);

    }
    //转变颜色
    public void setColor(View view) {
        myView.setcolor(Color.GREEN);
    }
    //加速
    public void setjia(View view) {
        myView.jiasu();
    }
    //减速
    public void setjian(View view) {
        myView.jiansu();
    }
    //暂停
    public void setPase(View view) {
        myView.pause();
    }
}
