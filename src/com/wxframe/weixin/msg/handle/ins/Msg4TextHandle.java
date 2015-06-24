package com.wxframe.weixin.msg.handle.ins;

import org.w3c.dom.Document;

import com.wxframe.weixin.msg.Msg;
import com.wxframe.weixin.msg.Msg4Head;
import com.wxframe.weixin.msg.body.Msg4Text;
import com.wxframe.weixin.msg.handle.MsgHandle;
import com.wxframe.weixin.passages.WXSession;

/**
 * 处理文本消息
 * @author GeneZ
 * @version 1.0.0
 */
public class Msg4TextHandle extends MsgHandle{

	@Override
	public void doHandle(Msg4Head head, Document document, WXSession session) {
		String type = head.getMsgType();
		if(Msg.MSG_TYPE_TEXT.equals(type)){
			Msg4Text msg = new Msg4Text(head);
			msg.read(document);
			onMsgHandle(msg);
			
			//TODO 例子
			Msg4Text reMsg = new Msg4Text();
			reMsg.setFromUserName(msg.getToUserName());
			reMsg.setToUserName(msg.getFromUserName());
			reMsg.setCreateTime(msg.getCreateTime());
			 
			reMsg.setContent("呵呵,谢谢您给我发消息");
			reMsg.setFuncFlag("0"); 
			session.callback(reMsg);
			return;
		}
		getNextHandle().doHandle(head, document, session);
	}

}
