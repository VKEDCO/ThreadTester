package org.vkedco.mobappdev.thread_tester_app;

/*
 ****************************************************
 * ThreadTestActivity is the main activity of the
 * ThreadTester application. ThreadTester application
 * illsutrates one simple point: the thread execution
 * order is not guaranteed. If we want to guarantee the
 * thread execution order, Thread.join() must be used.
 * If th is a Thread, then th.join() ensures that the
 * current thread gets to run only after th completes
 * its run().
 * 
 * ThreadTester.threadTest_01() runs two threads without
 * join(). Repeated runs show that thread execution order
 * is not guaranteed. 
 * 
 * ThreadTester.threadTest_02() runs two threads with
 * join(), thus ensuring that both threads complete their
 * Runnables before the main thread displays the results.
 * 
 ****************************************************
 */

import android.app.Activity;
import android.os.Bundle;

public class ThreadTesterActivity extends Activity {
    static final String LOGTAG = ThreadTesterActivity.class.getSimpleName() + "LOGTAG";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //ThreadTester.threadTest_01();
        ThreadTester.threadTest_02();
    }
}
