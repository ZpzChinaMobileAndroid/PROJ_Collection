package com.zhongji.collection.util;

import java.io.File;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

/**
 * 图片处理
 * http://blog.csdn.net/swust_chenpeng/article/details/10898451
 * http://blog.csdn.net/floodingfire/article/details/8144615
 * @author Administrator
 * 1.去掉截图
 */
public class PhotoUtils {

	private String TAG = "PhotoUtils";
	private Context context;
	private Bitmap bitmap;
	private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";// temp
	Uri imageUri = Uri.parse(IMAGE_FILE_LOCATION);// The Uri to store the big
													// bitmap
	private static final int CHOOSE_SMALL_PICTURE = 1;
	private static final int CHOOSE_BIG_PICTURE = 2;
	private static final int CHOOSE_TAKE_PICTURE = 3;
	public int outputX = 300;
	public int outputY = 300;
	
	public PhotoUtils(Context context){
		this.context = context;
	} 

	/**
	 * 对话框
	 */
	public void getDialog() {
		CharSequence[] items = { "相册", "相机" };
		new AlertDialog.Builder(context).setTitle("选择图片来源")
				.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (which == 0) {
							getBigPhoto();
						} else {
							Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
							intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
							((Activity)context).startActivityForResult(intent, CHOOSE_TAKE_PICTURE);//or TAKE_SMALL_PICTURE
						}
					}
				}).create().show();
	}
	
	/**
	 * 对话框--拍照
	 */
	public void getDialogPhoto() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		((Activity)context).startActivityForResult(intent, CHOOSE_TAKE_PICTURE);//or TAKE_SMALL_PICTURE
	}

	/**
	 * 照相
	 */
	public void getTakePhoto() {
		Intent intent = new Intent("com.android.camera.action.CROP");
		setIntent(intent);
		intent.setDataAndType(imageUri, "image/*");
		intent.putExtra("return-data", false);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		((Activity)context).startActivityForResult(intent, CHOOSE_BIG_PICTURE); 
	}
	
	/**
	 * 相册-大图
	 */
	public void getBigPhoto() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		setIntent(intent);
		intent.putExtra("return-data", false);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		((Activity)context).startActivityForResult(intent, CHOOSE_BIG_PICTURE); 
	}

	/**
	 * 相册-小图
	 */
	public void getSamllPhoto() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		setIntent(intent);
		((Activity)context).startActivityForResult(intent, CHOOSE_SMALL_PICTURE);
	}

	/**
	 * 参数设置
	 * @return
	 */
	private Intent setIntent(Intent intent) {
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		intent.putExtra("return-data", false);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		return intent;
	}
	
	/**
	 * Activity回调
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 * @return
	 */
	public Bitmap onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case CHOOSE_BIG_PICTURE:
			Log.d(TAG, "CHOOSE_BIG_PICTURE: data = " + data);// it seems to be
																// null
			if (imageUri != null) {
				if(bitmap!=null){
					bitmap.recycle();
				}
				bitmap = decodeUriAsBitmap(imageUri);// decode bitmap
				delFile(imageUri.getPath());
				return bitmap;
			}
			break;
		case CHOOSE_SMALL_PICTURE:
			if (data != null) {
				if(bitmap!=null){
					bitmap.recycle();
				}
				bitmap = data.getParcelableExtra("data");
				return bitmap;
			} else {
				Log.e(TAG, "CHOOSE_SMALL_PICTURE: data = " + data);
			}
			break;
		case CHOOSE_TAKE_PICTURE:
			Log.d(TAG, "TAKE_BIG_PICTURE: data = " + data);//it seems to be null
			getTakePhoto();
			
			break;
		default:
			break;
		}
		
		return null;
	}

	private Bitmap decodeUriAsBitmap(Uri uri) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}
	
	public String saveBitmap(){
		if(bitmap==null){
			return "bitmap null";
		}
		return BitmapUtil.saveBitmap(context, System.currentTimeMillis()+".jpg", bitmap);
	}
	
	private void delFile(String path){
		File file = new File(path);
		file.delete();
	}
}
