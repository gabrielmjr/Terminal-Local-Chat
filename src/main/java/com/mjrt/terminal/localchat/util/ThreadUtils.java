package com.mjrt.terminal.localchat.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtils {
    public static final int NUMBER_OF_POOLS = 1;
    public static final ExecutorService threadPools = Executors.newFixedThreadPool(NUMBER_OF_POOLS);
}
