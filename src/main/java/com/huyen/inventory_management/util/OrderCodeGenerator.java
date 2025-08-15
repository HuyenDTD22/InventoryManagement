package com.huyen.inventory_management.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCodeGenerator {

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    private static final String REDIS_KEY_PREFIX = "ORDER_SEQUENCE:";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public enum OrderType {
        IMPORT("IMP"),
        EXPORT("EXP");

        private final String prefix;

        OrderType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    public String generateOrderCode(OrderType orderType) {
        String datePart = LocalDateTime.now().format(DATE_FORMATTER);
        String redisKey = REDIS_KEY_PREFIX + orderType.getPrefix() + ":" + datePart;

        Long sequence = redisTemplate.opsForValue().increment(redisKey);
        if (sequence == 1) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime midnight = now.toLocalDate().plusDays(1).atStartOfDay();
            long secondsUntilMidnight = java.time.Duration.between(now, midnight).getSeconds();

            redisTemplate.expire(redisKey, secondsUntilMidnight, TimeUnit.SECONDS);
        }
        
        String sequencePart = String.format("%03d", sequence);

        return orderType.getPrefix() + "-" + datePart + "-" + sequencePart;
    }
}
