package com.wxframe.weixin.msg.body;

import org.w3c.dom.Document;

import com.wxframe.weixin.WXXmlElementName;
import com.wxframe.weixin.msg.Msg;
import com.wxframe.weixin.msg.Msg4Head;

/**
 * 视频消息
 * @author GeneZ
 * @version 1.0.0
 */
public class Msg4Video extends Msg{
	// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String mediaId;
	// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	private String thumbMediaId;
	// 消息id，64位整型
	private String msgId;
 
	public Msg4Video() {
		this.head = new Msg4Head();
		this.head.setMsgType(Msg.MSG_TYPE_MUSIC);
	}
	
	public Msg4Video(Msg4Head head) {
		this.head = head;
	}

	@Override
	public void write(Document document) { }
	
	
	@Override
	public void read(Document document) {
		this.mediaId = getElementContent(document, WXXmlElementName.MEDIAID);
		this.thumbMediaId = getElementContent(document, WXXmlElementName.THUMBMEDIAID);
		this.msgId = getElementContent(document, WXXmlElementName.MSG_ID);
	}

	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the thumbMediaId
	 */
	public String getThumbMediaId() {
		return thumbMediaId;
	}

	/**
	 * @param thumbMediaId the thumbMediaId to set
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
}