package com.nurturecloud.util;

import com.nurturecloud.model.RelativeLocation;

public class RelativeLocationComparator{
    public static int compare(RelativeLocation o1, RelativeLocation o2) {
        return Double.compare(o1.getDistance(), o2.getDistance());
    }
}
