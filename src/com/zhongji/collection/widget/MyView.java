package com.zhongji.collection.widget;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

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
	private String[] colors = {"#FF0000","#FF2D2D","#FF5151","#ff7575","#FF9797","#FFB5B5","#FFD2D2","#FFECEC"};

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		mPath = new Path();
		mPath.setFillType(FillType.EVEN_ODD);
		mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.RED);
		mPaint.setXfermode(new PorterDuffXfermode(Mode.DARKEN));  
//		mPaint.setPathEffect(new CornerPathEffect(5));	//Ӱ�����
		mPaint.setStyle(Paint.Style.STROKE);
//		mPaint.setStrokeJoin(Paint.Join.ROUND);
//		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(6);
	}

	public MyView(Context context, AttributeSet attrs, int defStyle) {
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
		
		Paint paint = new Paint();
		paint.setStrokeWidth(2);
		paint.setStyle(Paint.Style.STROKE);
//		Shader mShader = new RadialGradient(getWidth()/2, getHeight()/2, getHeight(), Color.WHITE, Color.RED, TileMode.CLAMP);
//		Shader mShader = new LinearGradient(0, 0, 0, 30, Color.RED, Color.WHITE, TileMode.CLAMP);
//		paint.setShader(mShader); 
//		canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
		
//		Shader left = new LinearGradient(0, 0, 50, 50, Color.RED, Color.WHITE, TileMode.CLAMP);
//		paint.setShader(left); 
//		canvas.drawRect(0, 0, 50, 50, paint);
//		Shader right = new LinearGradient(getWidth(), 0, getWidth()-50, 50, Color.RED, Color.WHITE, TileMode.CLAMP);
//		paint.setShader(right); 
//		canvas.drawRect(getWidth()-50, 0, getWidth(), 50, paint);
//		
//		Shader mShader = new LinearGradient(0, 0, 0, 50, Color.RED, Color.WHITE, TileMode.CLAMP);
//		paint.setShader(mShader); 
//		canvas.drawRect(50, 0, getWidth()-50, 50, paint);
//		Shader mShader2 = new LinearGradient(0, 0, 50, 0, Color.RED, Color.WHITE, TileMode.CLAMP);
//		paint.setShader(mShader2); 
//		canvas.drawRect(0, 50, 50, getHeight()-50, paint);
//		Shader mShader3 = new LinearGradient(getWidth(), 0, getWidth()-50, 0, Color.RED, Color.WHITE, TileMode.CLAMP);
//		paint.setShader(mShader3); 
//		canvas.drawRect(getWidth()-50, 50, getWidth(), getHeight()-50, paint);
			
		for(int i=0;i<15;i++){
			paint.setColor(Color.rgb(255, 50 +i*10, 50 + i*10));
//			paint.setColor(Color.parseColor(colors[i]));
			canvas.drawRect(0+i*2, 0+i*2, getWidth()-i*2, getHeight()-i*2, paint);
			
		}
		

		canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
		canvas.drawPath(mPath, mPaint);

	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_start(float x, float y) {
		mPaint.setStyle(Paint.Style.STROKE);
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
		mPaint.setStyle(Paint.Style.FILL);
//		mCanvas.drawPath(mPath, mPaint);
		// kill this so we don't double draw
		mPath.reset();		//不显示
	}
	
	private float startX=0;
	private float startY=0;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
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
