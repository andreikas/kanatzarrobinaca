package com.robinkanatzar.android.rck.threadsandasynctaskexample;


public class RunnableClass implements Runnable{

    //PhotoTask mPhotoTask;

    @Override
    public void run() {
    /*
     * Code you want to run on the thread goes here
     */

        // Moves the current Thread into the background
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        /*
         * Stores the current Thread in the PhotoTask instance,
         * so that the instance
         * can interrupt the Thread.
         */
        //mPhotoTask.setImageDecodeThread(Thread.currentThread());

    }

}
