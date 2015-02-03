/*
 * Copyright (c) 2014. Green White Solutions LLC. Tashkent.
 */

package uz.greenwhite.slidingmenu.support.v10.util;

import java.util.concurrent.atomic.AtomicLong;

public final class SequenceGenerator {
    private static final AtomicLong sequence = new AtomicLong();

    private SequenceGenerator() {
    }

    public static long next() {
        return sequence.incrementAndGet();
    }
}
