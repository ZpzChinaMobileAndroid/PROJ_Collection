package com.zhongji.collection.widget;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView2 extends View {

	private Paint mPaint;


	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Path mPath;
	private Paint mBitmapPaint;

	private long mStartTime;
	private long mEndTime;

	private List<Bitmap> mBitmaps;

	private boolean isStart = false;

	private Handler mHandler;
	private Context mContext;

	public MyView2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		mPath = new Path();
		mPath.setFillType(FillType.EVEN_ODD);	//参考http://www.cnblogs.com/coding-way/p/3595653.html
		mBitmapPaint = new Paint(Paint.DITHER_FLAG);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.RED);
//		mPaint.setXfermode(new PorterDuffXfermode(Mode.DARKEN));  
//		mPaint.setPathEffect(new CornerPathEffect(5));
		mPaint.setStyle(Paint.Style.STROKE);
//		mPaint.setStrokeJoin(Paint.Join.ROUND);
//		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(10);
		
	}

	public MyView2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void setPaint(Paint paint) {
		this.mPaint = paint;
	}

	public void setBitmapMap(List<Bitmap> bitmaps) {
		mBitmaps = bitmaps;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(0x00000000);

		canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

		canvas.drawPath(mPath, mPaint);
	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_start(float x, float y) {
		mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}
	}

	private void touch_up() {
		mPath.lineTo(mX, mY);
		mPath.close();
//		mPath.lineTo(startX, startY);
		// commit the path to our offscreen
//		mPaint.setStyle(Paint.Style.FILL);
		mCanvas.drawPath(mPath, mPaint);
		// kill this so we don't double draw
		mPath.reset();
	}
	
	private float startX=0;
	private float startY=0;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mPaint.setStyle(Paint.Style.STROKE);
			clear();
			startX = x;
			startY = y;
			mEndTime = System.currentTimeMillis();
			touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			mEndTime = System.currentTimeMillis();
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			mEndTime = System.currentTimeMillis();
			touch_up();
			invalidate();
			
			break;
		}
		return true;
	}

	public void clear()  
	{  
	    Paint paint = new Paint();  
	    paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));  
	    mCanvas.drawPaint(paint);  
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC));  
	      
	    invalidate();  
	}  
	
	/**
	 * if (!isStart) {
				isStart = true;
				new Thread() {
					public void run() {
						while (isStart) {
							long time = System.currentTimeMillis();
							if (time >= mEndTime + 1000) {
								if (mBitmaps != null) {
									int width = mBitmap.getWidth();
									int height = mBitmap.getHeight();
									Matrix matrix = new Matrix();
									float scaleWidht = ((float) 60 / width);
									float scaleHeight = ((float) 80 / height);
									matrix.postScale(scaleWidht, scaleHeight);
									Bitmap newbmp = Bitmap.createBitmap(mBitmap, 0, 0, width, height,
											matrix, true);
									mBitmaps.add(newbmp);
								}
								mHandler.sendEmptyMessage(0);
								isStart = false;
							}
						}
					};
				}.start();
			}
	 */
}
