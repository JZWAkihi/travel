package com.jiang.travel.service;


import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class redisTest {

    @Test
    public void redisTest(){
        Jedis jedis = new Jedis("192.168.111.128", 7000);

        jedis.select(0);

        Set<String> keys = jedis.keys("*");

        keys.forEach(key-> System.out.println("keys = " + key));


        jedis.close();

    }


}
