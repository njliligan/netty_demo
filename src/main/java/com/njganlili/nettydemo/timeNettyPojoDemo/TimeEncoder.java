package com.njganlili.nettydemo.timeNettyPojoDemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author njgan
 * @description
 * @date 2022/2/11 12:56
 */
//public class TimeEncoder extends ChannelOutboundHandlerAdapter {
//    @Override
//    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//        UnixTime unixTime = (UnixTime) msg;
//        ByteBuf byteBuf = ctx.alloc().buffer(4);
//        byteBuf.writeInt((int)unixTime.value());
//        //按鸳鸯传递原始通道Promise，方便Netty在编码数据实际写入线路将其标记为成功或者失败
//        //其次我们没有电泳
//        ctx.write(byteBuf,promise);
//    }
//}
//进一步简化
public class TimeEncoder extends MessageToByteEncoder<UnixTime> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, UnixTime unixTime, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt((int)unixTime.value());
    }
}
