package com.wxframe.config;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import com.wxframe.util.AccessTokenUtil;
import com.wxframe.util.XmlUtil;
import com.wxframe.weixin.msg.handle.MsgHandle;

/**
 * 总体配置文件获取类
 * @author GeneZ
 * @version 1.0.0
 */
public class Config {
	private static Logger log = LogManager.getLogger(Config.class);
	
	private static volatile AccessToken accessToken;
	private static WeiXinInfoConfig weixinCfg;
	private static FrameInfoConfig frameCfg;
	
	static{
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("weixin.xml");
		try {
			Document document = XmlUtil.parse(is);
			weixinCfg = WeiXinInfoConfig.initConfig(document);
			frameCfg = FrameInfoConfig.initConfig(document);
			
			log.info("weixin.xml read info[ weixin-config:{}; frame-config:{} ]"
					, weixinCfg, frameCfg);
		} catch (Exception e) {
			log.error("weixin.xml read error! weixin-config:{}; frame-config:{}"
					, weixinCfg, frameCfg);
			e.printStackTrace();
		}
	}

	public static FrameInfoConfig getFrameCfg() {
		return frameCfg;
	}

	public static AccessToken getAccessToken() {
		return accessToken;
	}

	public static WeiXinInfoConfig getWeixinCfg() {
		return weixinCfg;
	}

	/**
	 * 定时从微信服务器获取AccessToken
	 */
	public static void startGetAccessToken(){
		Thread accessTokenThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true)  {
					log.info("startGetAccessToken");
		            AccessToken token = AccessTokenUtil.freshAccessToken(); // 从微信服务器刷新access_token
					if(token != null){
						accessToken = token;
						log.info("get access_token info success: access_token={}, expire_in={}");
					}else{
						log.warn("get access_token failed");
					}
		             
		            try{
		                if(null != accessToken){
		                    Thread.sleep((accessToken.getExpire_in() - 200) * 1000);  
		                }else{
		                    Thread.sleep(60 * 1000);    // 如果access_token为null，60秒后再获取
		                }
		            }catch(InterruptedException e){
		                try{
		                    Thread.sleep(60 * 1000);
		                }catch(InterruptedException e1){
		                    e1.printStackTrace();
		                }
		            }
		        }
			}
		});
		accessTokenThread.start();
	}

	/**
	 * 获取消息处理服务
	 * @return
	 */
	public static MsgHandle getMsgHandle() {
		return frameCfg.getFirstHandle();
	}
}
