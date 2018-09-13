package com.business.gateway.config;

import static feign.Util.ensureClosed;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;

/**
 * @describe FeignClient配置
 * @author wupeng
 * @createtime 2017年9月12日
 */
@Configuration
public class FeignClientConfig {
	
	@Bean
	public Decoder feignDecoder() {
		final Gson gson = new Gson();
		return new Decoder() {
			@Override
			public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
				if (response.status() == 404) return Util.emptyValueOf(type);
				if (response.body() == null) return null;
				Reader reader = response.body().asReader();
				try {
					if (byte[].class.equals(type)) {
				        return Util.toByteArray(response.body().asInputStream());
				      }else if(String.class.equals(type)){
				    	  return Util.toString(response.body().asReader());
				      }else{
				    	  return gson.fromJson(reader, type);
				      }
				} catch (JsonIOException e) {
					if (e.getCause() != null && e.getCause() instanceof IOException) {
						throw IOException.class.cast(e.getCause());
					}
					throw e;
				} finally {
					ensureClosed(reader);
				}
			}
		};
	}

}
