package com.example.fyproject;

import android.provider.BaseColumns;

public class UserContract {

    private UserContract() {
        // Private constructor to prevent instantiation of the class
    }

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "login";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_EMAIL_ADDRESS = "email_address";
        public static final String COLUMN_CONDITION = "health condition";
    }
}
