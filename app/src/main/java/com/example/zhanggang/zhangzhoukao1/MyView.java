package com.example.zhanggang.zhangzhoukao1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * 类作用：
 * 时  间：2017/9/4 - 15:11.
 * 创建人：张刚
 */

public class MyView extends View {

    //自定义的属性
    private int huancolor;
    private float huanwidth;
    //当前画笔的颜色
    private int color_select;

    private Paint paint;

    public MyView(Context context) {
        super(context);
        initView(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        huancolor = typedArray.getColor(R.styleable.MyView_huancolor, Color.RED);
        color_select = huancolor;  //将当前的颜色存入变量中
        huanwidth = typedArray.getDimension(R.styleable.MyView_huanwidth, 5);

    }

    private void initView(Context context) {
        paint = new Paint();
    }

    public void setcolor(int color) {
        if (color_select != color) {
            color_select = color;
        } else {
            color_select = huancolor;
        }

    }

    private float pivotX;
    private float pivotY;
    private float radios = 130;  //圆的半径
    private float currentDegree = 0;  //画布旋转的度数

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(color_select);  //设置变量颜色值
        paint.setStrokeWidth(huanwidth); //设置环的宽
        paint.setStyle(Paint.Style.STROKE);  //设置空心

        //获得圆得圆心
        pivotX = getWidth() / 2;
        pivotY = getHeight() / 2;
        canvas.drawCircle(pivotX, pivotY, radios, paint); //绘制空心圆

        canvas.save();

        //旋转画布，旋转度数大的话，视觉上看着是旋转快的
        canvas.rotate(currentDegree, pivotX, pivotY);
        Path path = new Path();
        //从哪里开始画  A开始画
        path.moveTo(pivotX + radios, pivotY);   //中心点+半径
        //从A点到D点 画一条直线
        path.lineTo(pivotX + radios - 20, pivotY - 20);
        //从D点到B点 过一条直线
        path.lineTo(pivotX + radios, pivotY + 20);
        //从B点到C点 画一条直线
        path.lineTo(pivotX + radios + 20, pivotY - 20);
        //闭合
        path.close();

        paint.setStyle(Paint.Style.FILL); //
        paint.setColor(Color.BLACK);
        canvas.drawPath(path, paint);
        canvas.restore();  //重复转

        currentDegree += 1 * speed;  //画布的速度 * 画布旋转的速度

        if (!isPause) {  //如果不等于fase就重绘
            invalidate();
        }
    }

    //定义一个画布旋转的速度 点击加或减时的变量
    private int speed = 1;
    private boolean isPause = false;

    public void jiasu() {
        ++speed; //增加画布旋转速度
        if (speed >= 20) {
            speed = 20;
            Toast.makeText(getContext(), "已经快如闪电了", Toast.LENGTH_SHORT).show();
        }
    }

    public void jiansu() {
        --speed; //减小画布旋转速度
        if (speed <= 1) {
            speed = 1;
        }
    }

    public void pause() {
        //如果为fase  设置为true 重绘
        if (isPause) {
            isPause = !isPause;
            invalidate();
        } else {  //如果是true 设置为fase
            isPause = !isPause;
        }
    }
}
