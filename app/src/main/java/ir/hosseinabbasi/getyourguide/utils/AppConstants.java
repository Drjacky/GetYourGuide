package ir.hosseinabbasi.getyourguide.utils;

/**
 * Created by Dr.jacky on 2017/05/19.
 *
 */

public final class AppConstants {
    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "abbasi_getyourguide.db";
    public static final Integer DB_VERSION = 2;

    public static final String SEED_DATABASE_REVIEWS = "reviews.json";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
