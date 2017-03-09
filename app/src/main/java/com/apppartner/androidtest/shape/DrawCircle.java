package com.apppartner.androidtest.shape;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

/**
 * Draw Circle bitmap image based on radius & margin parameter
 */

public class DrawCircle implements com.squareup.picasso.Transformation {
    private int mRadius;
    private int mMargin;
    private Paint mPaint;

    public DrawCircle(int radius, int margin) {
        this.mRadius = radius;
        this.mMargin = margin;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawRoundRect(new RectF(mMargin, mMargin, source.getWidth() - mMargin, source.getHeight() - mMargin), mRadius, mRadius, mPaint);

        if (source != output) {
            source.recycle();
        }
        return output;
    }

    @Override
    public String key() {
        return "circle";
    }
}
