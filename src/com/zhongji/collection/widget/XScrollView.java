package com.zhongji.collection.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class XScrollView extends ScrollView {

	protected Context ctx = null;

	public XScrollView(Context context) {
		super(context);
		ctx = context;

		// TODO Auto-generated constructor stub
	}

	public XScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		ctx = context;
	}

	public XScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		ctx = context;
	}



	protected OnScrollStateChanged sc = null;

	public void setOnScrollStateChanged(OnScrollStateChanged _sc) {
		sc = _sc;
	}

	@Override
	protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
			boolean clampedY) {
		// TODO Auto-generated method stub
		if(getScrollY() < 10){
			sc.ScrollTop();
		}else if (getScrollY() + getHeight() >= computeVerticalScrollRange()) {
			sc.ScrollBottom();
		}
		sc.Scroll();
		super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
	}

	public interface OnScrollStateChanged {
		public void ScrollTop();
		public void Scroll();
		public void ScrollBottom();
	}
	
//	Log.e("scroll view", "view.getMeasuredHeight() = " + scrollView.getMeasuredHeight()
//            + ", v.getHeight() = " + v.getHeight()
//            + ", v.getScrollY() = " + v.getScrollY()
//            + ", view.getChildAt(0).getMeasuredHeight() = " + scrollView.getChildAt(0).getMeasuredHeight());
}
