package com.zneik.wavesblockexplorer.Helper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Helper {
    public static final String TIMESTAMP_FORMAT = "dd.MM.yyyy HH:mm:ss";

    public static String getStringFromTimestamp(Long timestamp) {
        Timestamp tsHeaders = new Timestamp(timestamp);
        return new SimpleDateFormat(TIMESTAMP_FORMAT, new Locale("ru"))
                .format(tsHeaders);
    }
}
