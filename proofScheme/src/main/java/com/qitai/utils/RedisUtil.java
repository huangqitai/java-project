package com.qitai.utils;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.AssertionImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
    private RedisTemplate<String, Object> redisTemplate;
    private RedisTemplate<String, Object> redisTemplateString;



    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public void setRedisTemplateString(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplateString = redisTemplate;
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return值
     */
    public static Object get(String key) {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        com.qitai.utils.RedisUtil redisUtil=(com.qitai.utils.RedisUtil)wac.getBean("redisUtil");
        //redisUtil.redisTemplate.setValueSerializer(new StringRedisSerializer());
        return key == null ? null : redisUtil.redisTemplateString.opsForValue().get(key);
    }

    /*
     *普通缓存放入
     *@param key键
     * @param value值
     *@return true成功false失败*/
    public static boolean set(String key, Object value,long timeOut) {
        try {
            WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
            com.qitai.utils.RedisUtil redisUtil=(com.qitai.utils.RedisUtil)wac.getBean("redisUtil");
            //redisUtil.redisTemplate.setValueSerializer(new StringRedisSerializer());
            redisUtil.redisTemplateString.opsForValue().set(key, value,timeOut, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void hset(String key, String userId) {

        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        com.qitai.utils.RedisUtil redisUtil=(com.qitai.utils.RedisUtil)wac.getBean("redisUtil");
        // key序列化
        //redisUtil.redisTemplate.setKeySerializer(new StringRedisSerializer());
        //val实例化
        redisUtil.redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //key=spring:session:sessions:xxxxxxx
        /*HashOperations<String, Object, Object> oo = redisUtil.redisTemplate.opsForHash();
        Set<Object> keys = oo.keys(key);
        // 获取redis
        Map<Object,Object> objectMap = redisUtil.redisTemplate.opsForHash().entries(key);
        Object creationTime = oo.get(key, sessionid);*/
        //设置cas的登录凭证（此步相当于模拟完成了原来cas的登录）
        AttributePrincipal principal = new AttributePrincipalImpl(userId);
        //objectMap.put(AbstractCasFilter.CONST_CAS_ASSERTION, new AssertionImpl(principal));
        // 如果redis里无当日的api访问数据，存入redis
        redisUtil.redisTemplate.opsForHash().put(key,"sessionAttr:"+AbstractCasFilter.CONST_CAS_ASSERTION,new AssertionImpl(principal));
    }
    /*
     *移除缓存*/
    public static boolean remove(String key) {
        try {
            WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
            com.qitai.utils.RedisUtil redisUtil=(com.qitai.utils.RedisUtil)wac.getBean("redisUtil");
            redisUtil.redisTemplateString.delete(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     *移除缓存*/
    public static boolean removeAll() {
        try {
            WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
            com.qitai.utils.RedisUtil redisUtil=(com.qitai.utils.RedisUtil)wac.getBean("redisUtil");
            Set<String> keys = redisUtil.redisTemplateString.keys("*");
            redisUtil.redisTemplateString.delete(keys);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 重置缓存时间
     */
    public static boolean expire(String key,int timeout){
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        com.qitai.utils.RedisUtil redisUtil=(com.qitai.utils.RedisUtil)wac.getBean("redisUtil");
        //设置过期时间
        return redisUtil.redisTemplateString.expire(key,timeout ,TimeUnit.SECONDS);
    }
}
