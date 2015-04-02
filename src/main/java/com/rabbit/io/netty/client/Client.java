package com.rabbit.io.netty.client;

import com.rabbit.io.netty.TimeClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	public static void main(String[] args) {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap boot = new Bootstrap();
			ChannelFuture f = boot.channel(NioSocketChannel.class)
				.group(group)
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new TimeClientHandler());
					}
				})
				.connect("localhost", 8888)
				.sync();
			
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			
		} finally {
			group.shutdownGracefully();
		}
	}
}
