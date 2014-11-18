package com.zhongji.collection.util;

/**
 * 弹窗工具类
 * 
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.entity.Contacts;
import com.zhongji.collection.interfaces.ContactsDialogCilck;

public class DialogUtils {

	public static EditText et_contacts_username, et_contacts_userphone,
			et_contacts_companyname, et_contacts_companyaddress;
	public static Button bt_contacts_save;
	public static TextView tv_contacts_post;
	public static String[] category1 = { "北京市", "天津市", "上海市", "重庆市", "河北省",
			"山西省", "内蒙古省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省",
			"江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西壮族自治区省", "海南省", "四川省",
			"云南省", "贵州省", "陕西省", "甘肃省", "宁夏回族自治区", "青海省", "新疆维吾尔自治区", "西藏自治区",
			"台湾省", "香港特别行政区", "澳门特别行政区" };

	public static String[] category2 = { "北京市", "天津市", "上海市", "重庆市", "石家庄市",
			"唐山市", "秦皇岛市", "邯郸市", "邢台市", "保定市", "张家口市", "承德市", "沧州市", "廊坊市",
			"衡水市", "太原市", "大同市", "阳泉市", "长治市", "晋城市", "朔州市", "晋中市", "运城市",
			"忻州市", "临汾市", "吕梁市", "呼和浩特市", "包头市", "乌海市", "赤峰市", "通辽市", "鄂尔多斯市",
			"呼伦贝尔市", "巴彦淖尔市", "乌兰察布市", "兴安盟市", "锡林郭勒盟市", "阿拉善盟市", "沈阳市", "大连市",
			"鞍山市", "抚顺市", "本溪市", "丹东市", "锦州市", "营口市", "阜新市", "辽阳市", "盘锦市",
			"铁岭市", "朝阳市", "葫芦岛市", "长春市", "吉林市", "四平市", "辽源市", "通化市", "白山市",
			"松原市", "白城市", "延边朝鲜族自治州", "哈尔滨市", "齐齐哈尔市", "鸡西市", "鹤岗市", "双鸭山市",
			"大庆市", "伊春市", "佳木斯市", "七台河市", "牡丹江市", "黑河市", "绥化市", "大兴安岭市", "南京市",
			"无锡市", "徐州市", "常州市", "苏州市", "南通市", "连云港市", "淮安市", "盐城市", "扬州市",
			"镇江市", "泰州市", "宿迁市", "杭州市", "宁波市", "温州市", "嘉兴市", "湖州市", "绍兴市",
			"金华市", "衢州市", "舟山市", "台州市", "丽水市", "合肥市", "巢湖市", "芜湖市", "蚌埠市",
			"淮南市", "马鞍山市", "淮北市", "铜陵市", "安庆市", "黄山市", "滁州市", "阜阳市", "宿州市",
			"六安市", "亳州市", "池州市", "宣城市", "福州市", "厦门市", "莆田市", "三明市", "泉州市",
			"漳州市", "南平市", "龙岩市", "宁德市", "南昌市", "共青城市", "景德镇市", "萍乡市", "九江市",
			"新余市", "鹰潭市", "赣州市", "吉安市", "宜春市", "抚州市", "上饶市", "济南市", "青岛市",
			"淄博市", "枣庄市", "东营市", "烟台市", "潍坊市", "济宁市", "泰安市", "威海市", "日照市",
			"莱芜市", "临沂市", "德州市", "聊城市", "滨州市", "菏泽市", "郑州市", "开封市", "洛阳市",
			"平顶山市", "安阳市", "鹤壁市", "新乡市", "焦作市", "濮阳市", "许昌市", "漯河市", "三门峡市",
			"南阳市", "商丘市", "信阳市", "周口市", "驻马店市", "武汉市", "黄石市", "十堰市", "宜昌市",
			"襄阳市", "鄂州市", "荆门市", "孝感市", "荆州市", "黄冈市", "咸宁市", "随州市",
			"恩施土家族苗族自治州", "仙桃市", "潜江市", "天门市", "神农架林区", "长沙市", "株洲市", "湘潭市",
			"衡阳市", "邵阳市", "岳阳市", "常德市", "张家界市", "益阳市", "郴州市", "永州市", "怀化市",
			"娄底市", "湘西土家族苗族自治州", "广州市", "深圳市", "珠海市", "佛山市", "江门市", "东莞市",
			"中山市", "韶关市", "肇庆市", "惠州市", "梅州市", "汕头市", "汕尾市", "河源市", "阳江市",
			"清远市", "湛江市", "茂名市", "潮州市", "揭阳市", "云浮市", "南宁市", "柳州市", "桂林市",
			"梧州市", "北海市", "防城港市", "钦州市", "贵港市", "玉林市", "百色市", "贺州市", "河池市",
			"来宾市", "崇左市", "海口市", "三亚市", "五指山市", "琼海市", "儋州市", "文昌市", "万宁市",
			"东方市", "三沙市", "定安县", "屯昌县", "澄迈县", "临高县", "白沙黎族自治县", "昌江黎族自治县",
			"乐东黎族自治县", "陵水黎族自治县", "保亭黎族苗族自治县", "琼中黎族苗族自治县", "成都市", "自贡市",
			"资阳市", "泸州市", "德阳市", "绵阳市", "广元市", "遂宁市", "内江市", "乐山市", "南充市",
			"眉山市", "宜宾市", "广安市", "达州市", "雅安市", "巴中市", "攀枝花市", "甘孜州", "凉山州",
			"阿坝州", "昆明市", "曲靖市", "玉溪市", "保山市", "昭通市", "丽江市", "潽洱市", "临沧市",
			"楚雄州", "大理州", "文山州", "西双版纳州", "红河州", "德宏州", "怒江州迪庆州", "贵阳市",
			"六盘水市", "遵义市", "安顺市", "毕节市", "铜仁市", "黔南市", "布依族苗族自治州",
			"黔西南布依族苗族自治州", "黔东南苗族侗族自治州", "西安市", "铜川市", "宝鸡市", "咸阳市", "渭南市",
			"延安市", "汉中市", "榆林市", "安康市", "商洛市", "兰州市", "嘉峪关市", "金昌市", "白银市",
			"天水市", "武威市", "张掖市", "平凉市", "酒泉市", "庆阳市", "定西市", "陇南市", "临夏回族自治州",
			"甘南藏族自治州", "银川市", "石嘴山市", "吴忠市", "固原市", "中卫市", "西宁市", "海东市",
			"玉树藏族自治州", "海北藏族自治州", "黄南藏族自治州", "海南藏族自治州", "果洛藏族自治州", "海西蒙古族自治州",
			"乌鲁木齐市", "克拉玛依市", "吐鲁番地区", "哈密地区", "昌吉回族自治州", "博尔塔拉蒙古自治州",
			"巴音郭楞蒙古自治州", "阿克苏地区", "克孜勒苏柯尔克孜州喀什地区", "和田地区", "伊犁哈萨克自治州", "塔城地区",
			"阿勒泰地区", "石河子市", "阿拉尔市", "图木舒克市", "五家渠市", "北屯市", "铁门关市", "双河市",
			"拉萨市", "昌都地区", "山南地区", "日喀则地区", "那曲地区", "阿里地区", "林芝地区", "香港岛",
			"九龙", "新界", "澳门半岛", "氹仔岛", "路环岛", "路氹城" };

	/**
	 * 多选择弹窗
	 * 
	 * @param context
	 * @param arrayid
	 */
	public static void showMultiChoiceDialog(Context context, String title, String[] items, final boolean[] checkedItems, DialogInterface.OnClickListener listener) {

		AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
		builder2.setTitle(title);
		builder2.setMultiChoiceItems(items, checkedItems,
				new AlertDialog.OnMultiChoiceClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1,
							boolean arg2) {
						// TODO 自动生成的方法存根
						checkedItems[arg1] = arg2; // 改变被操作列表的状态
					}
				});
		
		builder2.setPositiveButton("确定", listener);
		builder2.setNegativeButton("取消", null);
		builder2.show();
	}

	/**
	 * 单选择弹窗
	 * 
	 * @param context
	 * @param arrayid
	 */
	public static void showChoiceDialog(Context context, String title, String[] items,
			AlertDialog.OnClickListener listenr) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setItems(items, listenr);
		builder.show();
	}

	/**
	 * 联系人弹窗
	 * 
	 * @param context
	 * @param arrayid
	 */
	public static void showContactsDialog(final Context context,
			final String[] items, final Contacts contacts, final ContactsDialogCilck listener) {
		Activity act = (Activity) context;
		View view = act.getLayoutInflater().inflate(
				R.layout.dialog_relatilayout, null);
		final AlertDialog builder = new AlertDialog.Builder(context).create();
		builder.setView(view, 0, 0, 0, 0);
		et_contacts_username = (EditText) view
				.findViewById(id.et_contacts_username);// 添加姓名
		et_contacts_userphone = (EditText) view
				.findViewById(id.et_contacts_userpassword);// 添加电话
		tv_contacts_post = (TextView) view.findViewById(id.tv_contacts_post);// 岗位
		et_contacts_companyname = (EditText) view
				.findViewById(id.et_contacts_companyname);// 拍卖单位
		et_contacts_companyaddress = (EditText) view
				.findViewById(id.et_contacts_companyaddress);// 拍卖单位
		bt_contacts_save = (Button) view.findViewById(id.bt_contacts_save);// 保存
		
		if(contacts!=null){
			et_contacts_username.setText(contacts.getName());
			et_contacts_userphone.setText(contacts.getTelephone());
			tv_contacts_post.setText("岗位:"+contacts.getDuties());
			et_contacts_companyname.setText(contacts.getWorkAt());
			et_contacts_companyaddress.setText(contacts.getWorkAddress());
		}
		final InputMethodManager manager = (InputMethodManager) builder.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		RelativeLayout layout_all = (RelativeLayout) view.findViewById(R.id.layout_all);
		layout_all.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				
				manager.hideSoftInputFromWindow(builder.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				return false;
			}
		});
		// 岗位
		tv_contacts_post.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setItems(items, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						tv_contacts_post.setText("岗位:"+items[arg1]);
					}
				});
				builder.show();
			}
		});

		/**
		 * 保存
		 */
		bt_contacts_save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String name = et_contacts_username.getText().toString().trim();
				String phone = et_contacts_userphone.getText().toString().trim();
				String station = tv_contacts_post.getText().toString().trim();
				String companyname = et_contacts_companyname.getText().toString().trim();
				String companyaddress = et_contacts_companyaddress.getText().toString().trim();

				if (name.equals("")) {
					ToastUtils.getStance(context).showShortToast("请填写姓名！");
					return;
				}

				if (phone.equals("")) {
					ToastUtils.getStance(context).showShortToast("请填写电话！");
					return;
				}
				
				if(listener!=null){
					listener.save(contacts, name, phone, station.replace("岗位:", ""), companyname, companyaddress);
				}
				
				builder.dismiss();
			}
		});
		builder.show();
	}

	/**
	 * 区域
	 */
	public static void showDistrictDialog(final Context context,
			AlertDialog.OnClickListener listenr) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setItems(category1, listenr);
		builder.show();

	}

	/**
	 * 城市
	 */
	public static void showCityDialog(final Context context,
			AlertDialog.OnClickListener listenr) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setItems(category2, listenr);
		builder.show();

	}
	
}
