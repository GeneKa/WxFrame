package com.wxframe.weixin.msg.handle;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import com.wxframe.weixin.msg.Msg;
import com.wxframe.weixin.msg.Msg4Head;
import com.wxframe.weixin.passages.WXSession;

/**
 * 微信消息处理器（责任链模式处理）
 * @author GeneZ
 * @version 1.0.0
 */
public abstract class MsgHandle {
	protected MsgHandle nextHandle;
	protected List<HandleMsgListener> listeners = new ArrayList<HandleMsgListener>(1);
	
	/**
	 * 处理消息
	 * @param head 消息头
	 * @param document 消息xml体
	 */
	public abstract void doHandle(Msg4Head head, Document document, WXSession session);

	public MsgHandle getNextHandle() {
		return nextHandle;
	}

	public void setNextHandle(MsgHandle nextHandle) {
		this.nextHandle = nextHandle;
	}
	
	public void addListener(HandleMsgListener listener){
		listeners.add(listener);
	}
	
	/**
	 * 处理具体消息内容，以监听器的形式
	 * @param msg
	 */
	public void onMsgHandle(Msg msg){
		for(HandleMsgListener currentListener : listeners){
			currentListener.onMsgHandle(msg);
		}
	}
}
