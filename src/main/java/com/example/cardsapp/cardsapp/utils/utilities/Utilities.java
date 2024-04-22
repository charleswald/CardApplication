package com.example.cardsapp.cardsapp.utils.utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Utilities {
    public static LocalDateTime getCurrentDateTimeForZone() {
        // Set the desired time zone
        ZoneId zoneId = ZoneId.of("Africa/Nairobi");

        // Get the current date and time for the specified time zone
        LocalDateTime currentDateTime = LocalDateTime.now(zoneId);

        return currentDateTime;
    }

    public static String generateToken(String email) {
        // Set the desired time zone
        return "currentDateTime";
    }
}
