package com.example.pocketherbs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SquareOverlayView extends View {

    private Paint paint;

    public SquareOverlayView(Context context) {
        super(context);
        init();
    }

    public SquareOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SquareOverlayView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight() ;

        // Calculate the size of the square overlay
        int size = Math.min(width, height) * 3 / 4;

        // Calculate the coordinates of the square overlay
        int left = (width - size) / 2;
        int top = (height - size) / 2;
        int right = left + size;
        int bottom = top + size;

        // Draw the square overlay
        canvas.drawRect(left, top, right, bottom, paint);
    }
}
