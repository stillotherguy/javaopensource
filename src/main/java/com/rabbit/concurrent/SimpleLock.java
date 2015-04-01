package com.rabbit.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class SimpleLock {
	private class Sync extends AbstractQueuedSynchronizer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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
		
		private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
			in.defaultReadObject();
			setState(0);
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
