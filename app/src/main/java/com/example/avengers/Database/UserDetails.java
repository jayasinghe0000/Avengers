package com.example.avengers.Database;

import android.provider.BaseColumns;

public final class UserDetails {
//    public UserDetails(String first_name, EditText l_name, EditText email, EditText m_number, EditText work_area, EditText password, EditText re_password) {
//    }

//    public UserDetails(EditText f_name, EditText l_name, EditText email, EditText m_number, EditText work_area, EditText password, EditText re_password){
//
//        String first_name = f_name.getText().toString();
//
//    }

    public static class User implements BaseColumns{

        public static final String TABLE_NAME = "users";

        public static final String first_name = "f_name";
        public static final String last_name = "l_name";
        public static final String email = "email";
        public static final String mobile = "m_number";
        public static final String workArea = "work_area";
        public static final String password = "password";
        public static final String re_password = "re_password";
    }



}
