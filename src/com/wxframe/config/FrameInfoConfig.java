package com.wxframe.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.wxframe.weixin.msg.handle.HandleMsgListener;
import com.wxframe.weixin.msg.handle.MsgHandle;

/**
 * 框架配置信息，单例
 * @author GeneZ
 * @version 1.0.0
 */
public class FrameInfoConfig {
	private static Logger log = LogManager.getLogger(FrameInfoConfig.class);
	
	private List<MsgHandle> handles = new ArrayList<MsgHandle>();
	
	private FrameInfoConfig(){}
	
	public void addHandle(MsgHandle handle){
		handles.add(handle);
	}
	
	/**
	 * 初始化框架配置
	 * @param document 配置文件
	 * @return
	 */
	public static FrameInfoConfig initConfig(Document document) {
		FrameInfoConfig cfg = new FrameInfoConfig();
		NodeList nodeList = document.getElementsByTagName("handle");
		int length = nodeList.getLength();
		for (int i=0;i<length;i++) {
			Element node = (Element)nodeList.item(i);
			String attrClass = node.getAttribute("class");
			try {
				Object HandleObj = Class.forName(attrClass).newInstance();
				if(HandleObj instanceof MsgHandle){
					MsgHandle handle = (MsgHandle)HandleObj;
					log.info("handle class["+attrClass+"] ok");
					NodeList lisNodeList = node.getElementsByTagName("listener");
					int lisLength = lisNodeList.getLength();
					for(int j=0;j<lisLength;j++){
						Element lisNode = (Element)lisNodeList.item(j);
						String listenerStr = lisNode.getTextContent();
						Object lisObj = Class.forName(listenerStr).newInstance();
						if(lisObj instanceof HandleMsgListener){
							HandleMsgListener listener = (HandleMsgListener)lisObj;
							log.info("handle class["+attrClass+"],listener["+listenerStr+"] ok");
							handle.addListener(listener);
						}
					}
					MsgHandle lastHandle = cfg.getLastHandle();
					if(lastHandle!=null){
						lastHandle.setNextHandle(handle);
					}
					cfg.addHandle(handle);
				}
			} catch (Exception e) {
				log.error("tag of handle parse error, handle class["+attrClass+"] is not instanceof MsgHandle");
				e.printStackTrace();
			}
		}
		return cfg;
	}
	
	public MsgHandle getLastHandle(){
		if(handles.size()>0){
			return handles.get(handles.size()-1);
		}
		return null;
	}

	public MsgHandle getFirstHandle() {
		return handles.get(0);
	}

	@Override
	public String toString() {
		return "FrameInfoConfig [handles=" + handles + "]";
	}
}
