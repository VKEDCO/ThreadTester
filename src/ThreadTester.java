package org.vkedco.mobappdev.thread_tester_app;

/*
**********************************************************
* ThreadTester.threadTest_01() runs two threads without
* join(). Repeated runs show that thread execution order
* is not guaranteed. 
* 
* ThreadTester.threadTest_02() runs two threads with
* join(), thus ensuring that both threads complete their
* Runnables before the main thread displays the results.
***********************************************************
*/

import android.util.Log;

class MyJob implements Runnable {

	int mUpperBound = 0;
	long mSum = 0;
	
	public MyJob(int upper) {
		mUpperBound = upper;
	}
	
	@Override
	public void run() {
		Log.d(ThreadTesterActivity.LOGTAG, Thread.currentThread().getName() + " running");
		for(int i = 1; i < mUpperBound; i++) {
			mSum += i;
		}
		Log.d(ThreadTesterActivity.LOGTAG, Thread.currentThread().getName() + " done");
	}
	
	long getSum() {
		return mSum;
	}
}

public class ThreadTester {
	
	// The order in which threads run is not guaranteed.
	static void threadTest_01() {
		Log.d(ThreadTesterActivity.LOGTAG, Thread.currentThread().getName() + " running");
		MyJob job01 = new MyJob(100);
		MyJob job02 = new MyJob(200);
		new Thread(job01, "Thread01").start();
		new Thread(job02, "Thread02").start();
		Log.d(ThreadTesterActivity.LOGTAG, "job01's sum = " + job01.getSum());
		Log.d(ThreadTesterActivity.LOGTAG, "job02's sum = " + job02.getSum());
	}
	
	// thread execution order is ensured via join()
	static void threadTest_02() {
		Log.d(ThreadTesterActivity.LOGTAG, Thread.currentThread().getName() + " running");
		MyJob job01 = new MyJob(100);
		MyJob job02 = new MyJob(200);
		Thread thread01 = new Thread(job01, "Thread01");
		Thread thread02 = new Thread(job02, "Thread02");
		thread01.start();
		thread02.start();
		
		try {
			Log.d(ThreadTesterActivity.LOGTAG, Thread.currentThread().getName() + " joins "
					+ thread01.getName());
			// main thread is joined onto the end of thread01.
			thread01.join();
			Log.d(ThreadTesterActivity.LOGTAG, Thread.currentThread().getName() + " joins "
					+ thread02.getName());
			// main thread is joined onto the end of thread02.
			thread02.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d(ThreadTesterActivity.LOGTAG, "job01's sum = " + job01.getSum());
		Log.d(ThreadTesterActivity.LOGTAG, "job02's sum = " + job02.getSum());
		
	}
}
