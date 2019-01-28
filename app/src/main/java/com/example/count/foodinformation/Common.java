package com.example.count.foodinformation;

import Remote.RetrofitClient;
import Remote.Service;

public class Common
{
    private static String BASE_URL ="http://fiservice.fatihcankurtaran.com";
    public static Boolean Logon = false;

    public static Service GetService()
    {
        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}
