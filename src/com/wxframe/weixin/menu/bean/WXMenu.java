package com.wxframe.weixin.menu.bean;

import java.util.ArrayList;
import java.util.List;
/**
 * 微信菜单对象
 * @author GeneZ
 * @version 1.0
 */
public class WXMenu {

	//一级菜单按钮数组，个数应为1~3个
	public List<WXButton> button = new ArrayList<WXButton>(3);
	
	/**
	 * 添加一级菜单
	 * @param menu 
	 * @throws WeixinMenuOutOfBoundException
	 */
	public void addMenu(WXButton menu) {
		if(button.size() < 3){
			button.add(menu);
		}else{
			//TODO 处理异常
		}
	}
	
	
}
