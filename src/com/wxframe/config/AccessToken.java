package com.wxframe.config;

/**
 * 应用服务器请求微信服务器的信任Access_Token
 * <p>微信官方是2小时需要定时请求
 * @author GeneZ
 * @version 1.0.0
 */
public class AccessToken {
	private String access_token;
    private long expire_in;     // access_token有效时间，单位为妙
     
    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public long getExpire_in() {
        return expire_in;
    }
    public void setExpire_in(long expire_in) {
        this.expire_in = expire_in;
    }
}
