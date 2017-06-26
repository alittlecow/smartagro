package com.sywl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redicache 工具类
 * 
 */
@Component
public class RedisUtil {
	
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

//	/**
//	 * 批量删除key
//	 * 
//	 * @param pattern
//	 */
//	public void removePattern(final String pattern) {
//		Set<Serializable> keys = redisTemplate.keys(pattern);
//		if (keys.size() > 0)
//			redisTemplate.delete(keys);
//	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			stringRedisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return stringRedisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, String value) {
		boolean result = false;
		try {
			ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

//	/**
//	 * 写入缓存
//	 * 
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public boolean set(final String key, Object value, Long expireTime) {
//		boolean result = false;
//		try {
//			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//			operations.set(key, value);
//			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//			result = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
}
