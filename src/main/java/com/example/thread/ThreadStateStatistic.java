package com.example.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计当前线程状态
 * @Author Stone Mack
 * @Date 2020/3/4 13:44
 * @Version 1.0
 **/
public class ThreadStateStatistic {
    public void setStateMap(Map<Thread.State, Integer> stateMap) {
        this.stateMap = stateMap;
    }

    /** key=线程状态， value:当前状态的线程数量 */
    private Map<Thread.State, Integer> stateMap = new HashMap<>();

    public ThreadStateStatistic(){
    }

    public ThreadStateStatistic(Map<Thread.State, Integer> stateMap) {
        this.stateMap.putAll(stateMap);
    }

    public Map<Thread.State, Integer> getStateMap() {
        return stateMap;
    }

}
