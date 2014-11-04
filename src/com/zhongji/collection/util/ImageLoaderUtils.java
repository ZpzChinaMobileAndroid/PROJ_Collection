package com.zhongji.collection.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhongji.collection.android.phone.R;

public class ImageLoaderUtils {

	private static ImageLoaderUtils mImageLoaderUtils;
	private static DisplayImageOptions options;
	private static int drawable = R.drawable.ic_launcher;

	public ImageLoaderUtils() {
		
	}

	public static ImageLoaderUtils getInstance() {
		if (mImageLoaderUtils == null) {
			mImageLoaderUtils = new ImageLoaderUtils();
		}
		setOptions(drawable);
		return mImageLoaderUtils;
	}

	public static ImageLoaderUtils getInstance(int defaultdraw) {
		if (mImageLoaderUtils == null) {
			mImageLoaderUtils = new ImageLoaderUtils();
		}
		setOptions(defaultdraw);
		return mImageLoaderUtils;
	}

	private static void setOptions(int drawable) {
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(drawable)
				.showImageOnFail(drawable)
				.showImageOnLoading(drawable)
				.cacheInMemory(true).cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}
	
	public void displayImage(String uri, ImageView imageAware) {
		if (uri.startsWith("http://")) {
			ImageLoader.getInstance().displayImage(uri, imageAware, options);
		} else {
			ImageLoader.getInstance().displayImage("http://" + uri, imageAware,
					options);
		}
	}
}
