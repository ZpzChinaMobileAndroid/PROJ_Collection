package com.zhongji.collection.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Images;
import com.zhongji.collection.util.BitmapUtil;

/**
 * 广告viewpager
 * 
 * @author JokerDeng
 * 
 */
public class AdvertView extends LinearLayout {

	private Context context;
	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合
	private ImageView[] dots; // 图片上的的那些点
	private LinearLayout layout_dots; // 放置图片点的布局
	private MyAdapter adapter;
	private int currentItem = 0; // 当前图片的索引号
//	private boolean isScrolled = false;

	// 这里是模拟的数据源
//	private int[] imageIds = { R.drawable.advertise,
//			R.drawable.advertise, R.drawable.advertise };
//	private int[] imageIds = {R.drawable.launch_yd1,R.drawable.launch_yd2,R.drawable.launch_yd3};
//	private int[] imageIds = {R.drawable.launch_yd3,R.drawable.launch_yd2,R.drawable.launch_yd1};
	
	private List<String> lists = new ArrayList<String>();
	
//	public BitmapLoader mBitmapLoader;
//	protected BitmapLoader imageLoader;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	DisplayImageOptions options;
	private OnListener mOnListener;
	private List<Images> imglists = new ArrayList<Images>();
	private CheckListener mCheckListener;
	private List<Boolean> bools = new ArrayList<Boolean>();
	
	public void setmOnListener(OnListener mOnListener) {
		this.mOnListener = mOnListener;
	}

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 0);
					displayedImages.add(imageUri);
				}
			}
		}
	}

	// An ExecutorService that can schedule commands to run after a given delay,
	// or to execute periodically.
	private ScheduledExecutorService scheduledExecutorService;

	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};

	public void setLists(List<String> lists) {
		this.lists = lists;
//		initial();
	}

	public AdvertView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
//		initial();
//		this.imageLoader = new BitmapLoader(context);
	}

	public AdvertView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
//		initial();
//		this.imageLoader = new BitmapLoader(context);
	}

	public AdvertView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.context = context;
//		initial();
//		this.imageLoader = new BitmapLoader(context);
	}
	
	public void setImagesLists(List<Images> imglists){
		this.imglists = imglists;
		adapter.notifyDataSetChanged();
	}

	/**
	 * 初始化
	 */
	public void initial(int count) {
		adapter = new MyAdapter();
		LinearLayout layout = (LinearLayout) LinearLayout.inflate(context,
				R.layout.layout_advert, null);
		imageViews = new ArrayList<ImageView>();
//		mBitmapLoader = new BitmapLoader(context);
		
//		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
//		options = new DisplayImageOptions.Builder()
//		.showImageOnLoading(R.drawable.icon_default)
//		.showImageForEmptyUri(R.drawable.icon_default)
//		.showImageOnFail(R.drawable.icon_default)
//		.cacheInMemory(true)
//		.cacheOnDisk(true)
//		.considerExifParams(true)
//		.displayer(new RoundedBitmapDisplayer(20))
//		.build();
		
		// 初始化图片资源
		for (int i = 0; i < count; i++) {
//			Images img = imglists.get(i);
			ImageView imageView = new ImageView(context);
//			imageView.setScaleType(ScaleType.FIT_XY);
//			imageView.setImageBitmap(BitmapUtil.base64ToBitmap(img.getImgContent()));
//			imageView.setImageResource(R.drawable.ic_launcher);
//			mBitmapLoader.display(imageView, lists.get(i));
//			imageLoader.display(imageView,lists.get(i));
			imageView.setBackgroundColor(Color.BLACK);
			imageView.setId(i);
			bools.add(false);
			imageViews.add(imageView);
		}

		// 图片下面的小点
//		dots = new ImageView[imageIds.length];
//		layout_dots = (LinearLayout) layout.findViewById(R.id.layout_dots);
//		for (int j = 0; j < imageIds.length; j++) {
//			ImageView imageView = new ImageView(context);
//			imageView.setLayoutParams(new LayoutParams(15, 15));
//			dots[j] = imageView;
//			TextView tv = new TextView(context);
//			tv.setText("  ");
//			if (j == 0) {
//				// 默认进入程序后第一张图片被选中;
//				dots[j].setBackgroundResource(R.drawable.page_on);
//			} else {
//				dots[j].setBackgroundResource(R.drawable.page_off);
//			}
//
//			layout_dots.addView(tv);
//			layout_dots.addView(imageView);
//		}
//		// 如果只有一张图片，则不显示小点
//		if (lists.size() == 1) {
//			layout_dots.removeAllViews();
//		}

		viewPager = (ViewPager) layout.findViewById(R.id.viewpager);
		viewPager.setAdapter(adapter);// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
		viewPager.setCurrentItem(0);
		this.addView(layout);
		this.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		this.setBackgroundColor(Color.BLACK);
//		startSlide() ;
	}

	/**
	 * 开启自动滑动
	 */
	public void startSlide() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 3, 2,
				TimeUnit.SECONDS);
	}

	/**
	 * 关闭自动滑动
	 */
	public void stopSlide() {
		scheduledExecutorService.shutdown();
	}

	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	public void setCheckListener(CheckListener mCheckListener){
		this.mCheckListener = mCheckListener;
	}
	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
//			for (int i = 0; i < dots.length; i++) {
//				dots[position].setBackgroundResource(R.drawable.page_on);
//				if (position != i) {
//					dots[i].setBackgroundResource(R.drawable.page_off);
//				}
//			}
			if(mCheckListener!=null && !bools.get(position)){
				mCheckListener.change(position);
			}
		}

		public void onPageScrollStateChanged(int arg0) {
//			switch (arg0) {
//			case 1:// 手势滑动
//				isScrolled = false;
//				break;
//			case 2:// 界面切换
//				isScrolled = true;
//				break;
//			case 0:// 滑动结束
//
//				// 当前为最后一张，此时从右向左滑，则切换到第一张
//				if (viewPager.getCurrentItem() == viewPager.getAdapter()
//						.getCount() - 1 && !isScrolled) {
//					viewPager.setCurrentItem(0);
//				}
//				// 当前为第一张，此时从左向右滑，则切换到最后一张
//				else if (viewPager.getCurrentItem() == 0 && !isScrolled) {
//					viewPager
//							.setCurrentItem(viewPager.getAdapter().getCount() - 1);
//				}
//				break;
//			}
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}
	
	public void updateVIew(int pos, Images img){
		ImageView iv = imageViews.get(pos);
		bools.set(pos, true);
		iv.setImageBitmap(BitmapUtil.base64ToBitmap(img.getImgContent()));
		adapter.notifyDataSetChanged();
	}

	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyAdapter extends PagerAdapter {
		
		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return POSITION_NONE;
		}

		@Override
		public int getCount() {
			return imageViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			return imageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}
	}
	
	public interface OnListener{
		public void toActivity();
	}
	
	public interface CheckListener{
		public void change(int pos);
	}
}
