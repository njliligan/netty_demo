package com.njganlili.nettydemo.timeNettyDemo;

import io.netty.buffer.ByteBuf;
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
            final ByteBuf time = ctx.alloc().buffer(4);
            time.writeInt((int) (System.currentTimeMillis()/ 1000L + 2208988800L));
            final ChannelFuture channelFuture = ctx.writeAndFlush(time);
            //或者使用
            //channelFuture.addListener(ChannelFutureListener.CLOSE);
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                  assert channelFuture == future;
                  ctx.close();
                }
            });
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
