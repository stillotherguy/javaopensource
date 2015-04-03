package com.rabbit.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

public class TimeClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf bytes = (ByteBuf) msg;
		try {
			long currentTimeMillis = (bytes.readUnsignedInt() - 2208988800L) * 1000L;
			System.out.println(new Date(currentTimeMillis));
			String content = "haha";
			ByteBuf b = Unpooled.buffer(content.getBytes().length);
			b.writeBytes(content.getBytes());
			ctx.writeAndFlush(b);
		} finally {
			ReferenceCountUtil.release(bytes);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
