package com.wxframe.weixin.msg.handle.ins;

import org.w3c.dom.Document;

import com.wxframe.weixin.msg.Msg;
import com.wxframe.weixin.msg.Msg4Head;
import com.wxframe.weixin.msg.body.Msg4Voice;
import com.wxframe.weixin.msg.handle.MsgHandle;
import com.wxframe.weixin.passages.WXSession;

/**
 * 处理语音识别消息
 * @author GeneZ
 * @version 1.0.0
 */
public class Msg4VoiceHandle extends MsgHandle {

	@Override
	public void doHandle(Msg4Head head, Document document, WXSession session) {
		String type = head.getMsgType();
		if(Msg.MSG_TYPE_VOICE.equals(type)){
			Msg4Voice msg = new Msg4Voice(head);
			msg.read(document);
			onMsgHandle(msg);
			
			//TODO 待实现
		}
		getNextHandle().doHandle(head, document, session);
	}

}
