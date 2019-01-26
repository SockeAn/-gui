package com.taotao.rest.jedis;

import java.applet.AppletContext;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	
	@Test
	public void testJedisSingle() {
		//创建jedis对象
		Jedis jedis = new Jedis("192.168.12.130",6379);
		//调用jedis对象的方法，方法名称和redis命令一致
		jedis.set("hey1", "jedis test");
		String string = jedis.get("hey1");
		System.out.println(string);
		//关闭jedis
		jedis.close();
	}
	
	/**
	 * 使用连接池
	 */
	@Test
	public void testJedisPool() {
		//创建链接池
		JedisPool jedisPool = new JedisPool("192.168.12.130",6379);
		//从链接池中获得jedis对象
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get("hey1");
		System.out.println(string);
		//关闭jedis
		jedis.close();//使用完毕关闭jedis 资源才会回收 如果不关闭 每次使用都会重新连接一个新的 会把连接池装满
		jedisPool.close();
	}
	
	/**
	 * 集群
	 */
	@Test
	public void testJedisCluster() {
		HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.12.130", 7001));
		nodes.add(new HostAndPort("192.168.12.130", 7002));
		nodes.add(new HostAndPort("192.168.12.130", 7003));
		nodes.add(new HostAndPort("192.168.12.130", 7004));
		nodes.add(new HostAndPort("192.168.12.130", 7005));
		nodes.add(new HostAndPort("192.168.12.130", 7006));
		
		JedisCluster cluster = new JedisCluster(nodes);
		
		cluster.set("key1", "1000");
		String string = cluster.get("key1");
		System.out.println(string);
		
		cluster.close();
	}
	
	/**
	 * 单机
	 */
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext appletContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) appletContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("hey1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	/**
	 * 集群
	 */
	@Test
	public void testSpringJedis() {
		ApplicationContext appletContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster cluster = (JedisCluster) appletContext.getBean("redisClient");
		String string = cluster.get("key1");
		System.out.println(string);
		cluster.close();
	}

}
