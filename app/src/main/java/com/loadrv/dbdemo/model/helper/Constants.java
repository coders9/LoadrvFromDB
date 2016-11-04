package com.loadrv.dbdemo.model.helper;

/**
 * Created by mc11 on 3/11/16.
 */

public class Constants {

    public static final class HTTP {
       // public static final String BASE_URL = "http://services.hanselandpetal.com";
    }

    public static final class DATABASE {

        public static final String DB_NAME = "YoutubeVChannel";
        public static final int DB_VERSION = 1;
        public static final String TABLE_NAME = "vchannel";

        public static final String DROP_QUERY = "DROP TABLE IF EXIST " + TABLE_NAME;

        public static final String GET_VCHANNEL_QUERY = "SELECT * FROM " + TABLE_NAME;

        public static final String PRODUCT_ID = "productId";
      //  public static final String CATEGORY = "category";
        //public static final String PRICE = "price";
        public static final String DESCRIPTION = "description";
        public static final String TITLE = "Title";
        public static final String THUMBNAIL_URL = "thumbnail_url";
        public static final String PUBLIHED_AT = "datetime";


        public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "" +
                "(" + PRODUCT_ID + " INTEGER PRIMARY KEY not null AUTOINCREMENT," +
                TITLE+ " TEXT not null," +
                DESCRIPTION+ " TEXT not null," +
                PUBLIHED_AT + " TEXT not null," +
                THUMBNAIL_URL + " blob not null)";
    }

    public static final class REFERENCE {
        public static final String VCHANNEL = Config.PACKAGE_NAME + "vchannel";
    }

    public static final class Config {
        public static final String PACKAGE_NAME = "com.loadrv.dbdemo.";
    }
}
