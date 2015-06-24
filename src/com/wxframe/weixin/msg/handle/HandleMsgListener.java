package com.wxframe.weixin.msg.handle;

import com.wxframe.weixin.msg.Msg;

/**
 * 主要用于接收微信服务器消息的监听接口
 * @author Gene
 * @version 1.0.0
 * */
public interface HandleMsgListener {

	/**
	 * 收到消息处理
	 * @param msg
	 */
	public abstract void onMsgHandle(Msg msg);

}
