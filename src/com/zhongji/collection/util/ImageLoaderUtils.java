package com.zhongji.collection.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.network.HttpRestClient;

public class ImageLoaderUtils {

	private static ImageLoaderUtils mImageLoaderUtils;
	private static DisplayImageOptions options;
	private static int drawable = R.drawable.detial_img;

	public ImageLoaderUtils() {
		
	}

	public static ImageLoaderUtils getInstance(Context context) {
		if (mImageLoaderUtils == null) {
			mImageLoaderUtils = new ImageLoaderUtils();
		}
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
		setOptions(drawable);
		return mImageLoaderUtils;
	}

	public static ImageLoaderUtils getInstance(Context context, int defaultdraw) {
		if (mImageLoaderUtils == null) {
			mImageLoaderUtils = new ImageLoaderUtils();
		}
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
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
		if (!uri.startsWith("http://")) {
			uri = HttpRestClient.PIC_URL + uri;
		}
		ImageLoader.getInstance().displayImage(uri, imageAware,
				options);
	}
}
