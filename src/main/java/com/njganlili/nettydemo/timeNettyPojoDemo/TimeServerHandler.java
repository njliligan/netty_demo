package com.njganlili.nettydemo.timeNettyPojoDemo;

import io.netty.channel.*;

/**
 * @author njgan
 * @description
 * @date 2022/2/10 21:38
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * The {@link Channel} of the {@link ChannelHandlerContext} is now active
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
            final ChannelFuture channelFuture = ctx.writeAndFlush(new UnixTime());
            //或者使用
            channelFuture.addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * Gets called if a {@link Throwable} was thrown.
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
