package com.zhongji.collection.util;


import org.json.JSONArray;
import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Context;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.base.BaseSecondActivity;

/**
 * 讯飞语音输入对话框
 * 
 */
public class IflytekSpeechDialog extends BaseSecondActivity {
	private IflytekSpeechInterface iflytekSpeechInterface;
	private Context context;
	// speech
	// 语音听写UI
	private RecognizerDialog iatDialog;
	// 语音听写对象
	private SpeechRecognizer mIat;
	private EditText et_seach_voice_show;
	String text;

	//private StringBuffer resultBuffer = new StringBuffer();

	public IflytekSpeechDialog(Context context,IflytekSpeechInterface iflytekSpeechInterface) {
		this.iflytekSpeechInterface = iflytekSpeechInterface;
		this.context=context;
		onBind();
		mIat = SpeechRecognizer.createRecognizer(context, mInitListener);
		iatDialog = new RecognizerDialog(context, mInitListener);
		
		setParam();
	}
		
	@Override
	protected void init() {
		// TODO 自动生成的方法存根
		
		et_seach_voice_show=(EditText) findViewById(id.et_seach_voice_show);
		System.out.println("sssssssssss");
		et_seach_voice_show.setText(text);
		
		
	}

	
	
	public void setParam() {
		// 设置语言
		mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		// 设置语言区域
		mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
		// 设置语音前端点
		mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
		// 设置语音后端点
		mIat.setParameter(SpeechConstant.VAD_EOS, "1000");
		// 设置标点符号
		mIat.setParameter(SpeechConstant.ASR_PTT, "1");
		// 设置音频保存路径
		mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH,
				"/sdcard/iflytek/wavaudio.pcm");
	}

	/**
	 * 初始化监听器。
	 */
	private InitListener mInitListener = new InitListener() {

		@Override
		public void onInit(int code) {
			if (code == ErrorCode.SUCCESS) {
				iflytekSpeechInterface.onInit();
			}
		}
	};
	/**
	 * 听写UI监听器
	 */
	private RecognizerDialogListener recognizerDialogListener = new RecognizerDialogListener() {
		
		public void onResult(RecognizerResult results, boolean isLast) {
			// resultBuffer.append(JsonPaserUtils.parseIatResult(results
			// .getResultString()));
			// if (isLast) {
			// iflytekSpeechInterface.onResult(resultBuffer.toString());
			// }
			
			iflytekSpeechInterface.onResult(JsonParser.parseIatResult(results.getResultString()));

		}


		/**
		 * 识别回调错误.
		 */
		public void onError(SpeechError error) {
			iflytekSpeechInterface.onResult("errorCode: "	+ error.getErrorCode() + " message: " + error.getMessage());
		}

	};

	public void show() {
		// 显示听写对话框
		iatDialog.setListener(recognizerDialogListener);
		iatDialog.show();
	}

	/**
	 * 在activity的onCreate方法中调用 初始化引擎
	 * <p>
	 * Title: onBind
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	private void onBind() {
	//	SpeechUtility.getUtility().checkServiceInstalled();
	}

	/**
	 * 在activity onDestroy时调用，释放引擎相关
	 * <p>
	 * Title: onDestroy
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public void onDestroy() {
		// 退出时释放连接
		mIat.destroy();
		iatDialog.destroy();
	}

	public interface IflytekSpeechInterface {
		/**
		 * 讯飞语音引擎绑定成功回调
		 * <p>
		 * Title: onInit
		 * </p>
		 * <p>
		 * Description:
		 * </p>
		 */
		public void onInit();

		/**
		 * 听写结果回调
		 * <p>
		 * Title: onResult
		 * </p>
		 * <p>
		 * Description:
		 * </p>
		 * 
		 * @param result
		 */
		public void onResult(String result);

		/**
		 * 错误回调
		 * <p>
		 * Title: onError
		 * </p>
		 * <p>
		 * Description:
		 * </p>
		 * 
		 * @param error
		 */
		public void onError(String error);
	}

}

