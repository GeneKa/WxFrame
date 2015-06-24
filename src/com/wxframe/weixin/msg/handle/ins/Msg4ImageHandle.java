package com.wxframe.weixin.msg.handle.ins;

import org.w3c.dom.Document;

import com.wxframe.weixin.msg.Msg;
import com.wxframe.weixin.msg.Msg4Head;
import com.wxframe.weixin.msg.body.Msg4Image;
import com.wxframe.weixin.msg.handle.MsgHandle;
import com.wxframe.weixin.passages.WXSession;

/**
 * 处理图片消息
 * @author GeneZ
 * @version 1.0.0
 */
public class Msg4ImageHandle extends MsgHandle {

	@Override
	public void doHandle(Msg4Head head, Document document, WXSession session) {
		String type = head.getMsgType();
		if(Msg.MSG_TYPE_IMAGE.equals(type)){
			Msg4Image msg = new Msg4Image(head);
			msg.read(document);
			onMsgHandle(msg);
			
			//TODO 待实现
		}
		getNextHandle().doHandle(head, document, session);
	}

}
