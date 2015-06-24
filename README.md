# WxFrame
一个基于java的微信公众号的接入框架，通过该框架可以快速的对接微信公众平台

v1.0.0功能说明：
实现了微信服务器接入和消息的接收
日志系统基于log4j2

微信服务器访问入口：com.wxframe.weixin.passages.ReceiveWXReqServlet

weixin.xml说明：
weixin标签配置是微信平台必须的所属配置
frame标签是框架的配置，该框架处理消息基于handle责任链模式，每个handle表示一个处理类，每个处理类可以有多个监听。

开发者使用该框架只需要注册自己的消息处理类和监听器即可。在自己的实现类中完成相应的业务逻辑
