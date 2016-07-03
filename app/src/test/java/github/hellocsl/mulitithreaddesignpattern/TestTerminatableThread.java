package github.hellocsl.mulitithreaddesignpattern;

import com.thread.MyTerminatableThread;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 * Created by chensuilun on 16/7/2.
 */
public class TestTerminatableThread {
    @Spy
    MyTerminatableThread mMyTerminatableThread = new MyTerminatableThread();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        for (int i = 0; i < 3; i++) {
            mMyTerminatableThread.addTask(i);
        }
    }

    @Test
    public void testRunAll() throws Exception {
        Assert.assertFalse(mMyTerminatableThread.isToShutDown());
        mMyTerminatableThread.start();
        try {
            Thread.sleep(6 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mMyTerminatableThread.terminate(true);
        //the last time will be block
        Mockito.verify(mMyTerminatableThread, Mockito.times(4)).doRun();
        Assert.assertTrue(mMyTerminatableThread.isToShutDown());
    }

    @Test
    public void testTerminal() throws Exception {
        Assert.assertFalse(mMyTerminatableThread.isToShutDown());
        mMyTerminatableThread.start();
        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mMyTerminatableThread.terminate(true);
        Mockito.verify(mMyTerminatableThread, Mockito.times(1)).doRun();
        Assert.assertTrue(mMyTerminatableThread.isToShutDown());

    }
}
