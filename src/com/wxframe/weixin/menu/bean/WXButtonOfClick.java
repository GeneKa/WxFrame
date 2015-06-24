package com.wxframe.weixin.menu.bean;

/**
 * 点击类型按钮
 * <p>用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event 的结构给开发者（参考消息接口指南），
 * 并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互；
 * @author GeneZ
 * @version 1.0.0
 */
public class WXButtonOfClick extends WXButton {
	
	//click类型必须	 菜单KEY值，用于消息接口推送，不超过128字节
	private String key;

	public WXButtonOfClick(String name) {
		super(name);
		super.setType(TYPE_CLICK);
	}
	
	public WXButtonOfClick(String name,String key) {
		super(name);
		this.key = key;
		super.setType(TYPE_CLICK);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
