package com.intest.queen;

import java.util.concurrent.LinkedBlockingQueue;

public class PayLoadQueen {

	public static LinkedBlockingQueue<byte[]> pool = new LinkedBlockingQueue<byte[]>();

	public static void put(byte[] payLoad) throws InterruptedException {
		pool.put(payLoad);
	}

	public static byte[] take() throws InterruptedException {
		return pool.take();
	}
}
