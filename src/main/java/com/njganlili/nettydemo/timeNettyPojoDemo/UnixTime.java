package com.njganlili.nettydemo.timeNettyPojoDemo;

import java.util.Date;

/**
 * @author njgan
 * @description
 * @date 2022/2/11 12:44
 */
public class UnixTime {
    private final long value;

    public UnixTime(){
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }
    public UnixTime(long value){
        this.value = value;
    }
    public long value(){
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
