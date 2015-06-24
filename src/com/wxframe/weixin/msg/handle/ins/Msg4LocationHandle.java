package com.wxframe.weixin.msg.handle.ins;

import org.w3c.dom.Document;

import com.wxframe.weixin.msg.Msg;
import com.wxframe.weixin.msg.Msg4Head;
import com.wxframe.weixin.msg.body.Msg4Location;
import com.wxframe.weixin.msg.handle.MsgHandle;
import com.wxframe.weixin.passages.WXSession;

/**
 * 处理地理位置消息
 * @author GeneZ
 * @version 1.0.0
 */
public class Msg4LocationHandle extends MsgHandle {

	@Override
	public void doHandle(Msg4Head head, Document document, WXSession session) {
		String type = head.getMsgType();
		if(Msg.MSG_TYPE_LOCATION.equals(type)){
			Msg4Location msg = new Msg4Location(head);
			msg.read(document);
			onMsgHandle(msg);
			
			//TODO 待实现
		}
		getNextHandle().doHandle(head, document, session);
	}

}
