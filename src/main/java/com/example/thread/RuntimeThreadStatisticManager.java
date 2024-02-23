package com.example.thread;

import java.lang.Thread.State;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 统计当前进行运行过程中线程状态信息
 *
 * @Author Stone Mack
 * @Date 2020/3/4 13:39
 * @Version 1.0
 **/
public class RuntimeThreadStatisticManager {
    private static Set<String> states = null;
//    private static class LazyHolder{
//        private static final RuntimeThreadStatisticManager INSTANCE = new RuntimeThreadStatisticManager();
//    }
//
//    public static RuntimeThreadStatisticManager getInstance(){
//        return RuntimeThreadStatisticManager.LazyHolder.INSTANCE;
//    }
    {
        states = new HashSet<String>(8);
        for (State state : State.values()) {
            states.add(state.name());
        }
    }

    /**
     * 统计系统运行过程中的线程状态统计
     * @return
     */
    public ThreadStateStatistic runTimeThreadState() {
        Map<String, Thread> threads = ThreadUtil.getThreads();
        // 统计各种线程状态
        Map<State, Integer> stateCountMap = new HashMap<State, Integer>();
        for (State s : State.values()) {
            stateCountMap.put(s, 0);
        }
        for (Thread thread : threads.values()) {
            State threadState = thread.getState();
            Integer count = stateCountMap.get(threadState);
            stateCountMap.put(threadState, count + 1);
        }
        ThreadStateStatistic stateStatistic = new ThreadStateStatistic(stateCountMap);
        return stateStatistic;
    }
}
