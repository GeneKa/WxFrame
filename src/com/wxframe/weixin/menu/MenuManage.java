package com.wxframe.weixin.menu; 

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxframe.config.Config;
import com.wxframe.config.WeiXinInfoConfig;
import com.wxframe.util.AccessTokenUtil;
import com.wxframe.util.HttpUtil;
import com.wxframe.weixin.menu.bean.WXMenu;
 
/**
 * 菜单工具类
 * 提供创建、删除、查询菜单
 * @author GeneZ
 * @version 1.0
 */
public class MenuManage {
	
	/**
	 * 创建菜单
	 * @param json
	 * @throws WeixinException
	 */
	public static void create(WXMenu menu) {
		String url = Config.getWeixinCfg().getUrl(WeiXinInfoConfig.URL_MENU_CREATE)+ "?access_token=" + AccessTokenUtil.getAccessToken();
		String result = HttpUtil.sendHttpsPOST(url , JSON.toJSONString(menu)); 
		JSONObject obj = JSON.parseObject(result); 
		int errcode = obj.getIntValue("errcode");
		if(errcode>0){
			//TODO 处理异常
		}
	}

	
	/**
	 * 查询菜单
	 * @return
	 * @throws WeixinException
	 */
	public static String get() {
		String url = Config.getWeixinCfg().getUrl(WeiXinInfoConfig.URL_MENU_GET) + "?access_token=" + AccessTokenUtil.getAccessToken();
		return HttpUtil.sendHttpsGET(url);
	}
	
	
	
	/**
	 * 删除菜单
	 * @throws WeixinException
	 */
	public static void delete() {
		String url = Config.getWeixinCfg().getUrl(WeiXinInfoConfig.URL_MENU_DELETE) + "?access_token=" + AccessTokenUtil.getAccessToken();
		String result = HttpUtil.sendHttpsGET(url);
		JSONObject obj = JSON.parseObject(result); 
		int errcode = obj.getIntValue("errcode");
		if(errcode > 0){
			//TODO 处理异常
		}
	}
	
}

