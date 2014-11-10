package com.zhongji.collection.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

/**
 * 图片下载工具类
 * 
 * @author gaozhibin
 * 
 */
public class BitmapUtil {
	private static final String TAG = "BtimapUtil";

	/**
	 * 根据网址获得图片，优先从本地获取，本地没有则从网络下载
	 * 
	 * @param url
	 *            图片网址
	 * @param context
	 *            上下文
	 * @return 图片
	 */
	public static Bitmap getBitmap(String url, Context context) {
		Log.e(TAG, "------url=" + url);
		String imageName = url
				.substring(url.lastIndexOf("/") + 1, url.length());
		File file = new File(getPath(context), imageName);
		if (file.exists()) {
			Log.e(TAG, "getBitmap from Local");
			return BitmapFactory.decodeFile(file.getPath());
		}
		return getNetBitmap(url, file, context);
	}
	
	/**
	 * 取图片
	 * @param context
	 * @param pos
	 * @return
	 */
	public static Bitmap getBitmap(Context context, int pos){
		File file = new File(getPath(context));
		if(file!=null){
			File[] f = file.listFiles();
			if(pos<f.length){
				return BitmapFactory.decodeFile(f[pos].getPath());
			}
		}
		return null;
	}
	
	public static Drawable toDrawable(Context context, Bitmap bitmap){
		return new BitmapDrawable(context.getResources(), bitmap);
	}

	/**
	 * 保存图片
	 * @param context
	 * @param filename
	 * @param bitmap
	 * @return
	 */
	public static String saveBitmap(Context context, String filename, Bitmap bitmap){
		File file = new File(getPath(context), filename);
		try {
			FileOutputStream out = new FileOutputStream(file.getPath());
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
			return file.getAbsolutePath();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 根据传入的list中保存的图片网址，获取相应的图片列表
	 * 
	 * @param list
	 *            保存图片网址的列表
	 * @param context
	 *            上下文
	 * @return 图片列表
	 */
	public static List<Bitmap> getBitmap(List<String> list, Context context) {
		List<Bitmap> result = new ArrayList<Bitmap>();
		for (String strUrl : list) {
			Bitmap bitmap = getBitmap(strUrl, context);
			if (bitmap != null) {
				result.add(bitmap);
			}
		}
		return result;
	}

	/**
	 * 获取图片的存储目录，在有sd卡的情况下为 “/sdcard/apps_images/本应用包名/cach/images/”
	 * 没有sd的情况下为“/data/data/本应用包名/cach/images/”
	 * 
	 * @param context
	 *            上下文
	 * @return 本地图片存储目录
	 */
	private static String getPath(Context context) {
		String path = null;
		boolean hasSDCard = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
		String packageName = context.getPackageName() + "/cach/images/";
		if (hasSDCard) {
			path = "/sdcard/apps_images/" + packageName;
		} else {
			path = "/data/data/" + packageName;
		}
		File file = new File(path);
		boolean isExist = file.exists();
		if (!isExist) {

			file.mkdirs();

		}
		return file.getPath();
	}

	public static String getPathWeiSou(Context context) {
		String path = null;
		boolean hasSDCard = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
		String packageName = "/cach/";
		if (hasSDCard) {
			path = "/sdcard/weisou/";
		} else {
			path = "/data/data/" + packageName;
		}
		File file = new File(path);
		boolean isExist = file.exists();
		if (!isExist) {

			file.mkdirs();

		}
		return file.getPath();
	}
	
	public static String getNetBitmap2(String strUrl, File file, Context context) {
		String res = "";
		if("".equals(strUrl)){
			return "没有图片";
		}
		if(!strUrl.contains("http://")){
			strUrl= "http://"+strUrl;
		}
		Log.e(TAG, "getBitmap from net");
		System.out.println("+>?"+strUrl);
		System.out.println("+>?"+file.getAbsolutePath());
		Bitmap bitmap = null;
		if (NetUtil.isConnnected(context)) {
			try {
				URL url = new URL(strUrl);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.setDoInput(true);
				con.connect();
				InputStream in = con.getInputStream();
				bitmap = BitmapFactory.decodeStream(in);
				FileOutputStream out = new FileOutputStream(file.getPath());
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
				out.flush();
				out.close();
				in.close();
				res = file.getAbsolutePath();
			}catch (Exception e) {
				e.printStackTrace();
				res = "图片下载失败";
			} finally {

			}
		}
		return res;
	}
	
	/**
	 * 网络可用状态下，下载图片并保存在本地
	 * 
	 * @param strUrl
	 *            图片网址
	 * @param file
	 *            本地保存的图片文件
	 * @param context
	 *            上下文
	 * @return 图片
	 */
	private static Bitmap getNetBitmap(String strUrl, File file, Context context) {
		Log.e(TAG, "getBitmap from net");
		Bitmap bitmap = null;
		if (NetUtil.isConnnected(context)) {
			try {
				URL url = new URL(strUrl);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.setDoInput(true);
				con.connect();
				InputStream in = con.getInputStream();
				bitmap = BitmapFactory.decodeStream(in);
				FileOutputStream out = new FileOutputStream(file.getPath());
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
				out.flush();
				out.close();
				in.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}
		return bitmap;
	}

	public static File getFile(Context context, String filename){
		File file = new File(getPath(context), filename);
		return file;
	}
	
	public static void delFile(Context context, String filename){
		File file = new File(getPath(context), filename);
		file.delete();
	}
	
	/** 
	 * bitmap转为base64 
	 * @param bitmap 
	 * @return 
	 */  
	public static String bitmapToBase64(Bitmap bitmap) {  
	  
	    String result = null;  
	    ByteArrayOutputStream baos = null;  
	    try {  
	        if (bitmap != null) {  
	            baos = new ByteArrayOutputStream();  
	            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);  
	  
	            baos.flush();  
	            baos.close();  
	  
	            byte[] bitmapBytes = baos.toByteArray();  
	            result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);  
	        }  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        try {  
	            if (baos != null) {  
	                baos.flush();  
	                baos.close();  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    return result;  
	}  
	  
	/** 
	 * base64转为bitmap 
	 * @param base64Data 
	 * @return 
	 */  
	public static Bitmap base64ToBitmap(String base64Data) {  
	    byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);  
	    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);  
	}  
}
