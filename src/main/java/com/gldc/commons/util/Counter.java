package com.gldc.commons.util;

/**
 * Counter allows to have consecutive numbers generated uniquely.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class Counter {

    private Counter() {
        super();
    }

    private static long count = 0;

    public static long get() {
        return count;
    }

    public static long getAndIncrement() {
        if (count == Long.MAX_VALUE) {
            reset();
        }
        return ++count;
    }

    public static void reset() {
        count = 0;
    }

}
