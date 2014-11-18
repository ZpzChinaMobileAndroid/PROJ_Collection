package com.zhongji.collection.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

public class KeyboardLayout extends RelativeLayout {
	private static final String TAG = KeyboardLayout.class.getSimpleName();
	public static final byte KEYBOARD_STATE_SHOW = -3;
	public static final byte KEYBOARD_STATE_HIDE = -2;
	public static final byte KEYBOARD_STATE_INIT = -1;
	private boolean mHasInit;
	private boolean mHasKeybord;
	private int mHeight;
	private onKybdsChangeListener mListener;

	public KeyboardLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public KeyboardLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public KeyboardLayout(Context context) {
		super(context);
	}
	/**
	 * set keyboard state listener
	 */
	public void setOnkbdStateListener(onKybdsChangeListener listener){
		mListener = listener;
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (!mHasInit) {
			mHasInit = true;
			mHeight = b;
			if (mListener != null) {
				mListener.onKeyBoardStateChange(KEYBOARD_STATE_INIT);
			}
		} else {
			mHeight = mHeight < b ? b : mHeight;// ȡ���
		}
		if (mHasInit && mHeight > b) {
			if (mListener != null && !mHasKeybord) {
				mListener.onKeyBoardStateChange(KEYBOARD_STATE_SHOW);
			}
			
			mHasKeybord = true;
			Log.w(TAG, "show keyboard.......");
		}
		if (mHasInit && mHasKeybord && mHeight == b) {
			
			if (mListener != null && mHasKeybord) {
				mListener.onKeyBoardStateChange(KEYBOARD_STATE_HIDE);
			}
			mHasKeybord = false;
			Log.w(TAG, "hide keyboard.......");
		}
	}
	
	public interface onKybdsChangeListener{
		public void onKeyBoardStateChange(int state);
	}
}
