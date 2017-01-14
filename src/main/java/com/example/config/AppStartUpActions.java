package com.example.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.example.util.Utils;
import lombok.AllArgsConstructor;

@Configuration
public class AppStartUpActions {

    // Any startup sync action
    @PostConstruct
    public void startUpActionsSync() {
        Utils.ignoreSSL(); //Used to ignore SSL when atack to a https url

        // Start async tasks thread
        StartUpActionsAsync startActions = new StartUpActionsAsync();
        startActions.start();
    }

    @AllArgsConstructor
    private class StartUpActionsAsync extends Thread {

        @Override
        public void run() {
            // Any startup async action
        }

    }
}
