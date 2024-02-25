package com.irfan.resumebe.Service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async
    public void performAsyncTask() {
        // Simulate a long-running task
        try {
            Thread.sleep(5000); // Sleep for 5 seconds
            System.out.println("Async task completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
