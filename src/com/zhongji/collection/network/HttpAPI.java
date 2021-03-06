package com.zhongji.collection.network;

/**
 * @package com.suntime.microsearch.net
 * @description:
 * @version v1.0
 * @author JackieCheng
 * @email xiaming5368@163.com
 * @date 2014-7-23 下午4:21:46
 */
public class HttpAPI {

	public static final int HTTP_SUCCESS_CODE = 200;
	public static final int HTTP_TIME_OUT = 0;
	public static final int HTTP_NOT_FOUND_SERVICE = 404;
	public static final int HTTP_SERVER_ERROR = 500;
	public static final int HTTP_MISDAKE_CODE = 400;
	
	
	/** 用户登录 */
	public static final String USERS_LOGIN = "Users/login";
	
	/** 用户登出 */
	public static final String USERS_LOGOUT = "Users/LogOut";
	
	/**密码修改 */
	public static final String USERS_MODIFICATION="Users/";
	
	/**获取全部项目 */
	public static final String PROJECTS_ALL="Projects/";
	
	/**联系人 */
	public static final String CONTACTS ="BaseContacts/";
	
	/**图片 */
	public static final String PROJECTIMGS ="ProjectImgs/";
}
