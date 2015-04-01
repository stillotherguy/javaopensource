package com.rabbit.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class SimpleLock {
	private class Sync extends AbstractQueuedSynchronizer {
		@Override
		public boolean tryAcquire(int ignore) {
			if(compareAndSetState(0, 1)) {
				return true;
			}
			
			return false;
		}
		
		@Override
		public boolean tryRelease(int ignore) {
			setState(0);
			return true;
		}
	}
	
	private final Sync sync = new Sync();
	
	public void lock() {
		sync.acquire(1);
	}
	
	public void unlock() {
		sync.release(1);
	}
}
