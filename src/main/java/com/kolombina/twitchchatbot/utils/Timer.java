package com.kolombina.twitchchatbot.utils;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.Duration;

public class Timer {
    private long timeout;
    private boolean initiallyExpired;

    private long startTime;

    private Timer(Duration timeout, boolean initiallyExpired) {
        this.timeout = timeout.toNanos();
        this.initiallyExpired = initiallyExpired;
        this.startTime = getCurrentTime();
    }

    @JsonCreator
    public Timer(long timeout) {
        new Timer(Duration.ofSeconds(timeout), true);
    }

    public void restart() {
        initiallyExpired = false;
        startTime = getCurrentTime();
    }

    public boolean isExpired() {
        return initiallyExpired || getElapsedNanos() >= timeout;
    }

    private long getElapsedNanos() {
        return getCurrentTime() - startTime;
    }

    private long getCurrentTime() {
        return System.nanoTime();
    }

    @Override
    public String toString() {
        return "Timer{" +
                "timeout=" + timeout +
                ", initiallyExpired=" + initiallyExpired +
                ", startTime=" + startTime +
                '}';
    }
}
