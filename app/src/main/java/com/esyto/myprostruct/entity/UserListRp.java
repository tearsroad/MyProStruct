package com.esyto.myprostruct.entity;

import java.util.ArrayList;

/**
 * Created by lhxez on 2016/7/21.
 */

public class UserListRp extends MyBaseBean {
    public ArrayList<Account> accounts;
    public class Account{
        public String account;
        public String duty;
        public String status;
        public String userName;
        public String shopCode;


    }
}
