package com.qitai.cache;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
public class EhcacheTest {


    /**

     * 通过配置文件(ehcache.xml)来使用缓存
     * @author Administrator
     */
    public static void main(String[] args) throws FileNotFoundException {
        //通过读取ehcache配置文件来创建缓存管理器即CacheManager
        //E:\IJ-projects\proofScheme\src\main\java\com\qitai\cache\ehcache.xml
        String path = "E:\\IJ-projects\\proofScheme\\src\\main\\java\\com\\qitai\\cache\\ehcache.xml";
        File filename = new File(path);
        FileInputStream in = new FileInputStream(filename);
        CacheManager cacheManager = CacheManager.create(in);

        // 创建一个缓存实例（在配置文件中获取一个缓存实例）
        final Cache cache = cacheManager.getCache("helloworld1");

        final String key = "greeting";
        final String key1 = "greeting1";
        //创建一个数据容器来存放我们所创建的element
        final Element putGreeting = new Element(key, "Hello, World!");
        final Element putGreeting1 = new Element(key1, "Hello Ehcache");

        //将数据放入到缓存实例中
        cache.put(putGreeting);
        cache.put(putGreeting1);

        //取值
        final Cache cache2 = cacheManager.getCache("helloworld1");
        final Element getGreeting = cache2.get(key);
        final Element getGreeting1 = cache2.get(key1);

        // Print the value
        System.out.println("value======//========"+getGreeting.getObjectValue());
        System.out.println("value1=====//========"+getGreeting1.getObjectKey());
    }
}
