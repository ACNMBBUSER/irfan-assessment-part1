package com.irfan.resumebe.Controller;

import com.irfan.resumebe.Service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/task")
    public String triggerAsyncTask() {
        asyncService.performAsyncTask();
        return "Async task triggered";
    }


}
