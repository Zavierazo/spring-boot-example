package com.example.enums;

import lombok.Getter;

@Getter
public enum CacheEnum {

    GENERIC,//Cache without expiration & entry limit
    LIMITED_CACHE(-1, -1, 10),//Cache with limit of 10 elements & no expiration
    EXAMPLE(-1, 10, -1);//Cache with expiration on 10 seconds & no entry limit

    public static final String EXAMPLE_CACHE = "EXAMPLE";

    private long readExpiration = -1;// By default without expiration
    private long writeExpiration = -1;// By default without expiration
    private long limit = -1;// By default without expiration

    CacheEnum() {

    }

    CacheEnum(long readExpiration, long writeExpiration, long limit) {
        this.readExpiration = readExpiration;
        this.writeExpiration = writeExpiration;
        this.limit = limit;
    }
}
