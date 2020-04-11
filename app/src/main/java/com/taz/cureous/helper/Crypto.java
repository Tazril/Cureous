package com.taz.cureous.helper;

import android.os.Build;
import androidx.annotation.RequiresApi;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * This method generates Header as per the requirement of REST API server
 * https://apimedic.com/updated_terms?r=%2Fapitest
 * The server provide two URLs 1. Genuine with limited requests and 2. SandBox
 * @author Taz
 */
public class Crypto {
    private static final String USERNAME = "Pe62Q_GMAIL_COM_AUT";
    private static final String DUSERNAME = "tazrilparvez96@gmail.com";
    private static final String PASSWORD = "Qt54FrTf6p7W2YoXq";
    private static final String DPASSWORD = "c8L2Zin7Y6XwSg3k5";
    private static final String URL = "https://authservice.priaid.ch/login";
    private static final String DURL = "https://sandbox-authservice.priaid.ch/login";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args)  {
        byte[] bp = DPASSWORD.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(bp, "HmacMD5");
        String computedHashString = "";
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(secretKeySpec);
            byte[] result = mac.doFinal(DURL.getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            computedHashString = encoder.encodeToString(result);
            System.out.println(computedHashString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String headerkey = "Authorization";
        String headervalue = "Bearer " + DUSERNAME + ":" + computedHashString;
        System.out.println(headerkey);
        System.out.println(headervalue);
    }
}
