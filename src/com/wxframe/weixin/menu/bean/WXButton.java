package com.wxframe.weixin.menu.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信放在菜单中的按钮抽象类
 * @author GeneZ
 * @version 1.0
 */
public abstract class WXButton {
	
	/** click类型  */
	public static final String TYPE_CLICK = "click";
	/** view类型  */
	public static final String TYPE_VIEW  = "view";
	
	//菜单标题，不超过16个字节，子菜单不超过40个字节
	protected String name;
	//菜单类型，只有click和view两种
	protected String type;
	
	// 二级菜单数组，个数应为1~5个
	protected List<WXButton> sub_button = new ArrayList<WXButton>(5);
	
	public WXButton(String name) {
		this.name = name;
	}

	
	/**
	 * 添加子菜单
	 * @param menu
	 * @throws WeixinSubMenuOutOfBoundException
	 */
	public void addSubMenu(WXButton menu){
		if(sub_button.size() < 5){
			sub_button.add(menu);
		}else{
			//TODO 处理异常
		}
	}

	//该get和set方法仅为json转换使用，在实际开发中无用
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WXButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<WXButton> sub_button) {
		this.sub_button = sub_button;
	}
	
}
