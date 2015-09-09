package com.rabbit.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class TimeServerHandler extends ByteToMessageDecoder {

	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		final ByteBuf time = Unpooled.buffer(4);
		time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
		ctx.writeAndFlush(time);
//		f.addListener((future) -> {
//			assert f == future;
//			ctx.close();
//		});
	}
	
	
	
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}



	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf bytes, List<Object> out)
			throws Exception {
		System.out.println(ctx.channel().remoteAddress());
		System.out.println(bytes.readableBytes());
		/*if(bytes.isReadable(4)) {
			byte[] content = new byte[4];
			bytes.readBytes(content);
			String str = new String(content);
			System.out.println(str);
			if(str.equals("haha")) {
				final ByteBuf time = Unpooled.buffer(4);
				time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
				ctx.writeAndFlush(time);
			}
		}*/
	}



	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}
	
}
