package com.wxframe.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Xml解析工具
 * @author GeneZ
 * @version 1.0.0
 */
public class XmlUtil {
	private static DocumentBuilder builder;
	private static TransformerFactory tffactory;
	
	static{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		//格式化工厂对象
		tffactory = TransformerFactory.newInstance();
	}
	
	/**
	 * 通过输入流解析Document对象
	 * @param is
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document parse(InputStream is) throws SAXException, IOException{
		return builder.parse(is);
	}
	
	/**
	 * 创建一个Document对象
	 * @return
	 */
	public static Document newDocument(){
		return builder.newDocument();
	}
	
	/**
	 * 将Document对象格式化后输出
	 * @param document
	 * @param os
	 * @throws UnsupportedEncodingException
	 * @throws TransformerException
	 */
	public static void transformToOutput(Document document, OutputStream os) throws UnsupportedEncodingException, TransformerException{
		Transformer transformer = tffactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult(new OutputStreamWriter(os,"utf-8")));
	}
}
