package com.hipposa.license.hippolicensedemo;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

public class BaseActivity extends AppCompatActivity {

    protected static final String KEY_OTP = "KEY_OTP";
    protected static final String API_KEY = "39643166cafe264ab576329aeefa9d1f";
    private static final String HEX_CHARS = "0123456789abcdef";
    private Random random = new Random();

    protected String licenseKey = null;
    protected String hostId = null;
    protected int licenseCount = 0;

    protected void getHostInfo() {
        // GET Host Information by e.g. scanning QR Code
        licenseKey = "LICENSE_KEY"; //getRandomHexString(20, false);
        hostId = "112233DDEEFF"; //getRandomHexString(12, true);
        // GET License Count from e.g. CRM system
        licenseCount = 12;
    }

    private String getRandomHexString(int length, boolean toUpper) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(HEX_CHARS.charAt(random.nextInt(HEX_CHARS.length()) ) );
        if (toUpper) return sb.toString().toUpperCase();
        return sb.toString();
    }

    protected void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

}
