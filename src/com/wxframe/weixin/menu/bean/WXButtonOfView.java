package com.wxframe.weixin.menu.bean;
/**
 * 连接URL按钮
 * <p>用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的url值 （即网页链接），达到打开网页的目的，
 * 建议与网页授权获取用户基本信息接口结合，获得用户的登入个人信息。
 * @author GeneZ
 * @version 1.0.0
 */
public class WXButtonOfView extends WXButton {

	// view类型使用
	private String url;
	
	public WXButtonOfView(String name) {
		super(name);
		super.setType(TYPE_VIEW);
	}
	
	public WXButtonOfView(String name,String url) {
		super(name);
		this.url = url;
		super.setType(TYPE_VIEW);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
