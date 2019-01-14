package com.finleap.weather.service;

public interface DateCheckerService {

    /**
     * return date in Range plus 3
     * @param epochSecond
     * @return
     */
    boolean isDateInRange(long epochSecond);

    /**
     * return if time is between 6 and 18
     * @param epochSecond
     * @return
     */
    boolean isTimeInRange(long epochSecond);
}
