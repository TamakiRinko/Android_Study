package com.example.customize_control;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


/**
 * 需求：
 *      圆形红色按钮
 *      中间有一个白色数字
 *      起始为20
 *      每次点击减1
 */
public class TestRedButton extends View {

    private Paint paint;
    private Rect rect;
    private int number;

    public TestRedButton(Context context) {
        this(context, null);    //调第二个
    }

    public TestRedButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);//调第三个
    }

    public TestRedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);    //初始化
        init();
    }

    /**
     * init the view
     */
    private void init(){
        paint = new Paint();    //画布
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //做一个圆形的红色按钮
        //设置画布为红色
        paint.setColor(Color.RED);
        //画红色的圆
        canvas.drawCircle(0, 0, getWidth(), paint);
        //中间有一个数字，白色
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);

        String text = String.valueOf(number);
        paint.getTextBounds(text, 0, text.length(), rect);      //圆心的位置为0？
        int textWidth = rect.width();
        int textHeight = rect.height();


    }
}