package com.thread.pattern.termination;

/**
 * Created by chensuilun on 16/7/2.
 */
public class TerminationToken {


    public volatile boolean mIsShutDown;


    public void shutDown() {
        mIsShutDown = true;
    }

    public boolean isToShutDown() {
        return mIsShutDown;
    }


}
