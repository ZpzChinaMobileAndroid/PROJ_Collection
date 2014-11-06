package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.util.DialogUtils;

/**
 * 地勘阶段
 * @author Admin
 *
 */


public class ExplorationStageView implements OnClickListener {

	private Context context;
	public View view_explorationstage;
	public TextView tv_explorationcompany;//地勘公司
	
	public ExplorationStageView(Context context){
		this.context=context;
		view_explorationstage=View.inflate(context,R.layout.view_explorationstage,null);
		tv_explorationcompany=(TextView) view_explorationstage.findViewById(id.tv_explorationcompany);
	
		tv_explorationcompany.setOnClickListener(this);
	 }
	
      public View getView(){
		return view_explorationstage;
	
        }

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		
		if(arg0.getId()==R.id.tv_explorationcompany){
		//地勘公司	
		DialogUtils.showContactsDialog(context,R.array.geologicalexplorationunitspost);	
			
 		   }
	    }
     }
