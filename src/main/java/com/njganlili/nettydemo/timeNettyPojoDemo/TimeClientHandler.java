package com.njganlili.nettydemo.timeNettyPojoDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author njgan
 * @description
 * @date 2022/2/10 21:55
 */
//这里的Channel In bound HandlerAdapter 注意其中的IN，代表的是入站事件
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UnixTime unixTime = (UnixTime) msg;
        System.out.println(unixTime);
        ctx.close();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
