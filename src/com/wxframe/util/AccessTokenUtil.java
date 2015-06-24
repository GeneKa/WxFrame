package com.wxframe.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxframe.config.AccessToken;
import com.wxframe.config.Config;
import com.wxframe.config.WeiXinInfoConfig;

/**
 * 应用服务器请求微信服务器AccessToken工具类
 * @author GeneZ
 * @version 1.0.0
 */
public class AccessTokenUtil {

	/**
	 * 从微信服务器获取AccessToken
	 * @return
	 */
	public static AccessToken freshAccessToken() {
		String url = Config.getWeixinCfg().getUrl(WeiXinInfoConfig.URL_ACCESSTOKEN) + "?" + "grant_type="
				+Config.getWeixinCfg().getGrantType()
				+"&appid="+Config.getWeixinCfg().getAppId()+"&secret="+Config.getWeixinCfg().getSecretKey();

		String json = HttpUtil.sendHttpsGET(url);
		JSONObject obj = JSON.parseObject(json);
		String access_token = obj.getString("access_token");
		long expires_in = obj.getLongValue("expires_in");
		if(access_token!=null && !"".equals(access_token) 
				&& expires_in>0){
			AccessToken token = new AccessToken();
			token.setAccess_token(access_token);
			token.setExpire_in(expires_in);
			return token;
		}
		return null;
	}

	/**
	 * 获取token字符串
	 * @return
	 */
	public static String getAccessToken() {
		AccessToken token = Config.getAccessToken();
		return token.getAccess_token();
	}

}
