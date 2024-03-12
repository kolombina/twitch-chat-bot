package com.kolombina.twitchchatbot.utils;

import java.time.Duration;

public class Timer {
    private long timeout;
    private boolean initiallyExpired;

    private long startTime;

    Timer(Duration timeout, boolean initiallyExpired) {
        this.timeout = timeout.toNanos();
        this.initiallyExpired = initiallyExpired;
        this.startTime = getCurrentTime();
    }

    /**
     * Новый таймер с заданной длительностью
     */
    public static Timer of(Duration timeout) {
        return new Timer(timeout, false);
    }

    /**
     * Новый истекший таймер с заданной длительностью
     */
    public static Timer expired(Duration timeout) {
        return new Timer(timeout, true);
    }


    public Timer restart(Duration timeout) {
        this.timeout = timeout.toNanos();
        startTime = getCurrentTime();
        return restart();
    }

    public Timer restart() {
        initiallyExpired = false;
        startTime = getCurrentTime();
        return this;
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
