package com.ece656.house.web.interceptor;

import com.ece656.house.common.model.Admin;

public class AdminContext {
    private static final ThreadLocal<Admin> ADMIN_HODLER = new ThreadLocal<>();

    public static void setAdmin(Admin admin){
        ADMIN_HODLER.set(admin);
    }

    public static void remove(){
        ADMIN_HODLER.remove();
    }

    public static Admin getAdmin(){
        return ADMIN_HODLER.get();
    }
}