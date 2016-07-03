package com.thread.pattern.termination;

/**
 * Created by chensuilun on 16/7/2.
 */
public abstract class AbstractTerminatableThread extends Thread implements Terminatable {

    private TerminationToken mTerminationToken;

    public AbstractTerminatableThread() {
        mTerminationToken = new TerminationToken();
    }

    @Override
    public void interrupt() {
        terminate();
    }


    @Override
    public void terminate() {
        mTerminationToken.shutDown();
        try {
            doTerminate();
        } finally {
            super.interrupt();
        }
    }

    public void terminate(boolean waitUntilTerminated) {
        terminate();
        if (waitUntilTerminated) {
            try {
                //Waits for this thread to die.
                this.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void run() {
        super.run();
        Exception exception = null;
        for (; ; ) {
            try {
                if (isToShutDown()) {
                    return;
                }
                doRun();
            } catch (Exception e) { //response for Interrupt()
                exception = e;
            } finally {
                doCleanUp(exception);
            }
        }
    }


    public boolean isToShutDown() {
        return mTerminationToken.isToShutDown();
    }

    public abstract void doRun() throws Exception;

    /**
     * release resource etc...
     *
     * @param e
     */
    protected void doCleanUp(Exception e) {

    }

    /**
     * ready for terminate
     */
    protected void doTerminate() {

    }


}
