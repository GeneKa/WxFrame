package com.wxframe.weixin.passages;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wxframe.config.Config;
import com.wxframe.util.SecurityUtil;

/**
 * 用于接收微信服务器发送过来的请求<br> 
 * 微信服务器发送请求get仅进行鉴权，post进行交互数据<br>
 * @author GeneZ
 * @version 1.0.0
 */
public class ReceiveWXReqServlet extends HttpServlet {
	private static final long serialVersionUID = 1198921180131201751L;
	
	private static Logger log = LogManager.getLogger(ReceiveWXReqServlet.class);

	/**
	 * 接受微信鉴权入口
	 * <p>报文：<br>
	 * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。<br>
	 * timestamp	时间戳<br>
	 * nonce	随机数<br>
	 * echostr	随机字符串<br>
	 */
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串
        
        log.info("signature: {} ", signature);
        log.info("timestamp: {} ", signature);
        log.info("nonce: {} ", nonce);
        log.info("echostr: {} ", echostr);
        
 		List<String> list = new ArrayList<String>(3) {
			private static final long serialVersionUID = 559685132093844666L;

			public String toString() {
 				return this.get(0) + this.get(1) + this.get(2);
 			}
 		};
 		list.add(Config.getWeixinCfg().getToken());
 		list.add(timestamp);
 		list.add(nonce);
 		Collections.sort(list);
 		String tmpStr = SecurityUtil.encodeBySHA1(list.toString());
 		Writer out = response.getWriter();
 		if (signature.equals(tmpStr)) {
 			out.write(echostr);  //请求验证成功，返回随机码
 		} else {
 			log.error("request Illegal");
 			out.write("");
 		}
        out.flush();
        out.close();
    }
	
	/**
	 * 接收微信消息入口，文本、图片、地理位置、音乐等
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		InputStream is = request.getInputStream();
		OutputStream os = response.getOutputStream();
		
		try {
			WXSession session = new WXSession(is,os);
			session.execute();
			session.close();
		} catch (Exception e) {
			log.error("msessage handle error");
			e.printStackTrace();
		}
		
//		int temp = 0;
//		int count = 0;
//		byte[] b = new byte[1024];
//		while((temp = is.read()) != -1){
//			b[count++] = (byte)temp;
//		}
//		System.out.println(new String(b));
//		
//		PrintWriter pw = response.getWriter();
//		pw.print("<xml><ToUserName><![CDATA[oodRIt6O_pso7_YE8RkB_ke-HrU0]]></ToUserName><FromUserName><![CDATA[gh_0c5b26536959]]></FromUserName><CreateTime>1435619345</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>");
//		pw.flush();
//		pw.close();
//		is.close();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		Config.startGetAccessToken();
	}
}
