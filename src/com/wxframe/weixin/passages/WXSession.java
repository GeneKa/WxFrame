package com.wxframe.weixin.passages;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.transform.TransformerException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.wxframe.config.Config;
import com.wxframe.util.XmlUtil;
import com.wxframe.weixin.msg.Msg;
import com.wxframe.weixin.msg.Msg4Head;
import com.wxframe.weixin.msg.handle.MsgHandle;

/**
 * 抽象的微信会话，此会话声明周期在一个请求响应内。
 * <p>需要通过实现具体的消息处理类进行消息处理
 * @author GeneZ
 * @version 1.0.0
 */
public class WXSession {
	private static Logger log = LogManager.getLogger(WXSession.class);
	
	private InputStream is;
	private OutputStream os;
	
	/**
	 * 实例化会话session
	 * @param is 微信http输入流
	 * @param os 微信http输出流
	 */
	public WXSession(InputStream is, OutputStream os){
		this.is = is;
		this.os = os;
	}

	/**
	 * 执行当前消息处理
	 */
	public void execute() {
		try {
			Document document = XmlUtil.parse(is);
			Msg4Head head = new Msg4Head();
			head.read(document);
		 
			MsgHandle handle = Config.getMsgHandle();
			handle.doHandle(head, document, this);
		} catch (SAXException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	/**
	 * 关闭当前对象的执行处理io流程
	 */
	public void close() {
		try {
			if(is != null){
				is.close();
			}
			if(os != null){
				os.flush();
				os.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 回传消息给微信服务器
	 * <p>只能在接收到微信服务器消息后，才能调用此方法
	 * @param msg 消息对象
	 * @throws TransformerException 
	 * @throws UnsupportedEncodingException 
	 * */
	public void callback(Msg msg) {
		Document document = XmlUtil.newDocument();
		msg.write(document);
		try {
			XmlUtil.transformToOutput(document, os);
		} catch (Exception e) {
			// TODO 统一异常
			log.error("transform document error! document: "+document);
			e.printStackTrace();
		}
	}
}
