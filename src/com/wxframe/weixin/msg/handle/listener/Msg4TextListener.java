package com.wxframe.weixin.msg.handle.listener;

import com.alibaba.fastjson.JSON;
import com.wxframe.weixin.msg.Msg;
import com.wxframe.weixin.msg.handle.HandleMsgListener;

/**
 * 处理文本消息监听器
 * @author GeneZ
 * @version 1.0.0
 */
public class Msg4TextListener implements HandleMsgListener {

	@Override
	public void onMsgHandle(Msg msg) {
		System.out.println("Msg4TextListener被调用："+JSON.toJSONString(msg));
	}

}
