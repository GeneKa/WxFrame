package com.wxframe.config;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

/**
 * 微信配置信息,单例
 * @author GeneZ
 * @version 1.0.0
 */
public class WeiXinInfoConfig {
	/**
	 * 获取tokenURL
	 */
	public static String URL_ACCESSTOKEN = "access-token";
	/**
	 * 创建菜单URL
	 */
	public static String URL_MENU_CREATE = "menu-create";
	/**
	 * 获取菜单URL
	 */
	public static String URL_MENU_GET = "menu-get";
	/**
	 * 删除菜单URL
	 */
	public static String URL_MENU_DELETE = "menu-delete";
	
	private String appId;
	private String token;
	private String secretKey;
	private String grantType;
	private Map<String,String> urlMap = new HashMap<String,String>();
	
	private WeiXinInfoConfig(){}
	
	public String getUrl(String key){
		return urlMap.get(key);
	}
	public void putUrl(String key,String url){
		urlMap.put(key, url);
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public Map<String, String> getUrlMap() {
		return urlMap;
	}
	public void setUrlMap(Map<String, String> urlMap) {
		this.urlMap = urlMap;
	}
	
	/**
	 * 初始化微信配置
	 * @param document
	 * @return
	 */
	public static WeiXinInfoConfig initConfig(Document document) {
		WeiXinInfoConfig cfg = new WeiXinInfoConfig();
		cfg.setAppId(document.getElementsByTagName("appid").item(0).getTextContent());
		cfg.setGrantType(document.getElementsByTagName("grant-type").item(0).getTextContent());
		cfg.setSecretKey(document.getElementsByTagName("secret-key").item(0).getTextContent());
		cfg.setToken(document.getElementsByTagName("token").item(0).getTextContent());
		
		cfg.putUrl(URL_ACCESSTOKEN, document.getElementsByTagName(URL_ACCESSTOKEN).item(0).getTextContent());
		cfg.putUrl(URL_MENU_CREATE, document.getElementsByTagName(URL_MENU_CREATE).item(0).getTextContent());
		cfg.putUrl(URL_MENU_GET, document.getElementsByTagName(URL_MENU_GET).item(0).getTextContent());
		cfg.putUrl(URL_MENU_DELETE, document.getElementsByTagName(URL_MENU_DELETE).item(0).getTextContent());
		return cfg;
	}

	@Override
	public String toString() {
		return "WeiXinInfoConfig [appId=" + appId + ", token=" + token
				+ ", secretKey=" + secretKey + ", grantType=" + grantType
				+ ", urlMap=" + urlMap + "]";
	}
	
}
