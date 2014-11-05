package com.zhongji.collection.project;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;

/**
 * 地勘阶段
 * @author Admin
 *
 */


public class ExplorationStageView {

	public View view_explorationstage;
	public TextView tv_explorationcompany;//地勘公司
	
	public ExplorationStageView(Context context){
		view_explorationstage=View.inflate(context,R.layout.view_explorationstage,null);
		tv_explorationcompany=(TextView) view_explorationstage.findViewById(id.tv_explorationcompany);
	
	 }
	
      public View getView(){
		return view_explorationstage;
	
        }
     }
