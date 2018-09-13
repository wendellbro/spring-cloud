package com.business.gateway.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	/**
	 * GET请求
	 * @author wupeng
	 * @param url
	 * @param connectionTimeout	链接超时时间
	 * @return
	 */
	public static String httpGet(String url, int connectionTimeout){
		CloseableHttpClient httpClient = null;  
		HttpGet httpGet = null;  
		CloseableHttpResponse httpResponse = null;
		String result = null;  
		try {
			httpClient = HttpClientBuilder.create().build();
			httpGet = new HttpGet(url);
			httpResponse = httpClient.execute(httpGet);
			if(httpResponse != null){  
				HttpEntity resEntity = httpResponse.getEntity();  
				if(resEntity != null){  
					result = EntityUtils.toString(resEntity);  
				}  
			}  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if (httpResponse != null){
					httpResponse.close();
				}
				if (httpClient != null){
					httpClient.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String httpGet(String url){
		
		return httpGet(url, 5000);
	}
	
	/**
	 * POST请求：form参数：UTF-8
	 * @author wupeng
	 * @param url
	 * @param params
	 * @param charset  字符编码
	 * @return
	 */
	public static String httpPost(String url,Map<String,Object> paraMap){  

		return httpPost(url, paraMap, StandardCharsets.UTF_8, 5000);
	}
	
	
	/**
	 * POST请求：form参数
	 * @author wupeng
	 * @param url
	 * @param params
	 * @param charset  字符编码
	  * @param connectionTimeout	链接超时时间
	 * @return
	 */
	public static String httpPost(String url,Map<String,Object> params,Charset charset, int connectionTimeout){  
		CloseableHttpClient httpClient = null;  
		HttpPost httpPost = null;  
		CloseableHttpResponse httpResponse = null;
		String result = null;  
		try{
			httpClient = HttpClientBuilder.create().build();
			httpPost = new HttpPost(url);  
			//设置参数  
			List<NameValuePair> list = new ArrayList<NameValuePair>();  
			if (null != params) {
				for (Entry<String, Object> entry : params.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue() != null ? entry.getValue().toString() : "";
					list.add(new BasicNameValuePair(key,value));  
				}
			}
			if(list.size() > 0){  
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
				httpPost.setEntity(entity);  
			}  
			
			RequestConfig config = RequestConfig.custom().setConnectTimeout(connectionTimeout).build();	//设置请求和传输超时时间
			httpPost.setConfig(config);
			
			httpResponse = httpClient.execute(httpPost);  
			if(httpResponse != null){  
				HttpEntity resEntity = httpResponse.getEntity();  
				if(resEntity != null){  
					result = EntityUtils.toString(resEntity,charset);  
				}  
			}  
		}catch(Exception ex){  
			ex.printStackTrace();  
		}finally{
			try{
				if (httpResponse != null){
					httpResponse.close();
				}
				if (httpClient != null){
					httpClient.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return result;  
	}  
	
	/**
	 * POST请求：JSON参数
	 * @author wupeng
	 * @param url
	 * @param params
	 * @param charset  字符编码
	 * @param connectionTimeout	链接超时时间
	 * @return
	 */
	public static String httpJSONPost(String url, String params, Charset charset, int connectionTimeout){  
		CloseableHttpClient httpClient = null;  
		HttpPost httpPost = null;  
		CloseableHttpResponse httpResponse = null;
		String result = null;  
		try{
			httpClient = HttpClientBuilder.create().build();
			httpPost = new HttpPost(url);  
			
			httpPost.addHeader("Content-type","application/json; charset=utf-8");  
			httpPost.setHeader("Accept", "application/json");  
			httpPost.setEntity(new StringEntity(params, charset)); 
			
			RequestConfig config = RequestConfig.custom().setConnectTimeout(connectionTimeout).build();	//设置请求和传输超时时间
			httpPost.setConfig(config);
			
			httpResponse = httpClient.execute(httpPost);  
			if(httpResponse != null){  
				HttpEntity resEntity = httpResponse.getEntity();  
				if(resEntity != null){  
					result = EntityUtils.toString(resEntity,charset);  
				}  
			}  
		}catch(Exception ex){  
			ex.printStackTrace();  
		}finally{
			try{
				if (httpResponse != null){
					httpResponse.close();
				}
				if (httpClient != null){
					httpClient.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return result;  
	}  

	/**
	 * POST请求：JSON参数：UTF-8
	 * @author wupeng
	 * @param url
	 * @param params
	 * @return
	 */
	public static String httpJSONPost(String url, String params){
		
		return httpJSONPost(url, params, StandardCharsets.UTF_8, 5000);
	}

}


