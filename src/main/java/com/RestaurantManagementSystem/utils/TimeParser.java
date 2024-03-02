package com.RestaurantManagementSystem.utils;

import java.time.Duration;

public class TimeParser {
    /**
     * Return Duration from a string in format "HH:mm:ss".
     * @param formatStringHoursMinutesSeconds string in format "HH:mm:ss".
     * @return Duration object.
     */
    public static Duration FromFormatStringHoursMinutesSecondsToDuration(String formatStringHoursMinutesSeconds) {
        String[] parts = formatStringHoursMinutesSeconds.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }
}
