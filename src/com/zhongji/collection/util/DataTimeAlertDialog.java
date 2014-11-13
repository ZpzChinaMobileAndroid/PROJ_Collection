package com.zhongji.collection.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class DataTimeAlertDialog{

	private AlertDialog dialog;
	private DatePicker datepicker;
	private TimePicker timepicker;
	private Click clickListener;

	public void setClickListener(Click clickListener) {
		this.clickListener = clickListener;
	}

	public DataTimeAlertDialog(Context context) {
		// TODO Auto-generated constructor stub
		initial(context);
	}

	public DataTimeAlertDialog(Context context, boolean cancelable,OnCancelListener cancelListener) {
		// TODO Auto-generated constructor stub
		initial(context);
	}



	private void initial(final Context context) {
		// TODO Auto-generated method stub
		LinearLayout view = new LinearLayout(context);
		view.setOrientation(LinearLayout.VERTICAL);
		datepicker = new DatePicker(context);
		timepicker = new TimePicker(context);
		view.addView(datepicker);
		view.addView(timepicker);
		setTimeUnable();
//		String str = datepicker.getYear()+"年"+(datepicker.getMonth()+1)+"月"+datepicker.getDayOfMonth()+"日";
		 
		//隐藏日历模式
	//	datepicker.setCalendarViewShown(false);
		datepicker.init(datepicker.getYear(), datepicker.getMonth(), datepicker.getDayOfMonth(), new OnDateChangedListener() {
			
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				
			}
		}); 
		timepicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				
			}
		});
		
		dialog = new AlertDialog.Builder(context)
		.setTitle("选择时间")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(clickListener!=null){
					clickListener.sure(getDateStr(),getTimeStr());
					
				}
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(clickListener!=null){
					clickListener.cancel();
					
				}
			}
		})
		.setView(view).create();
		
		
	}
	
	
	/**
	 * 日期控件不可用
	 */
	public void setDateUnable(){
		datepicker.setVisibility(View.GONE);
	}
	
	/**
	 * 时间控件不可用
	 */
	public void setTimeUnable(){
		timepicker.setVisibility(View.GONE);
	}
	
	/**
	 * 获取日期
	 * @return
	 */
	public String getDateStr(){
		if(datepicker.getVisibility() == View.GONE){
			return "";
		}
		return datepicker.getYear()+"-"+(datepicker.getMonth()+1)+"-"+datepicker.getDayOfMonth()+""; 
	}
	
	/**
	 * 获取时间
	 * @return
	 */
	public String getTimeStr(){
		if(timepicker.getVisibility() == View.GONE){
			return "";
		}
		return timepicker.getCurrentHour()+":"+timepicker.getCurrentMinute(); 
	}
	
	public void show() {
		// TODO Auto-generated method stub
		dialog.show();
	}
	
	public interface Click{
		public void sure(String date, String time);
		public void cancel();
	}
}
