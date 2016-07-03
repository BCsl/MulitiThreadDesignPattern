package com.thread;

import com.thread.pattern.termination.AbstractTerminatableThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by chensuilun on 16/7/2.
 */
public class MyTerminatableThread extends AbstractTerminatableThread {
    private BlockingQueue<Integer> mRunTime;

    public MyTerminatableThread() {
        mRunTime = new ArrayBlockingQueue<Integer>(3);
    }

    public void addTask(int id) {
        mRunTime.add(id);
    }

    @Override
    public void doRun() throws Exception {
        int id = mRunTime.take();
        Thread.sleep(1000);
        System.out.printf("doRun" + id);
    }
}
