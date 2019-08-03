package com.example.customize_control;

import android.content.Context;
import android.content.res.TypedArray;
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
public class TestRedButton extends View implements View.OnClickListener {

    private Paint paint;
    private Rect rect;
    private int number = 20;
    //背景颜色
    private int backgroundColor;
    private int mtextSize;

    public TestRedButton(Context context) {
        this(context, null);    //调第二个
    }

    public TestRedButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);//调第三个
    }

    public TestRedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);    //初始化
        init(context, attrs);
    }

    /**
     * init the view
     */
    private void init(Context context, AttributeSet attrs){
        //不能将new操作放入onDraw，会不停调用
        paint = new Paint();    //画布
        rect = new Rect();      //矩形
        this.setOnClickListener(this);

        //获取刚刚定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TestRedButton);
        //得到属性，默认值为红色
        backgroundColor = typedArray.getColor(R.styleable.TestRedButton_backgroundColor, Color.BLUE);


        mtextSize = typedArray.getDimensionPixelSize(R.styleable.TestRedButton_mytextSize, DisplayUtil.px2sp(context, 18));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 做一个圆形的红色按钮
         */

        //设置画布为红色
        paint.setColor(Color.RED);
//        paint.setColor(backgroundColor);
        //画红色的圆
        //getWidth即为所在控件的Width，200dp，getHeight同理
        canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2, paint);

        //中间有一个数字，白色
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        String text = String.valueOf(number);
        paint.getTextBounds(text, 0, text.length(), rect);      //得到文字四周矩形的边距，存储在rect中
        int textWidth = rect.width();
        int textHeight = rect.height();
        //坐标为左下角的坐标
        canvas.drawText(text, getWidth()/2 - textWidth/2, getHeight()/2 + textHeight/2, paint);

    }

    @Override
    public void onClick(View v) {
        if(number > 0){
            number--;
            invalidate();   //请求
        }
        else{
            number = 20;
            invalidate();
        }
    }
}