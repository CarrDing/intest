package com.intest.queen;

import java.util.concurrent.LinkedBlockingQueue;

public class PublishQueen {

	public static LinkedBlockingQueue<byte[]> pool = new LinkedBlockingQueue<byte[]>();

	public static void put(byte[] paramSet) throws InterruptedException {
		pool.put(paramSet);
	}

	public static byte[] take() throws InterruptedException {
		return pool.take();
	}
}
