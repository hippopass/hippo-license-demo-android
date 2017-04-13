package com.hipposa.license.hippolicensedemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hipposa.lib.HippoLicenseManager;

public class MainActivity extends BaseActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private TextView gCodeInputView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gCodeInputView = (TextView) findViewById(R.id.gCodeInput);

        findViewById(R.id.generateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String gCode = gCodeInputView.getText().toString();
                    if (gCode == null || gCode.isEmpty()) {
                        showAlertDialog(getString(R.string.waringing_title),
                                getString(R.string.warning_gcode_empty));
                        return;
                    }
                    HippoLicenseManager lm = new HippoLicenseManager(API_KEY);
                    String otp = null;
                    try {
                        otp = lm.generateOTP(gCode, licenseKey, hostId, licenseCount);
                    } catch (Exception e) {
                        Log.e(TAG, "Failed to generateOTP: " + e.toString());
                    }
                    if (otp == null) {
                        showAlertDialog(getString(R.string.error_title),
                                getString(R.string.error_generate_otp_failed));
                    } else {
                        final Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        intent.putExtra(KEY_OTP, otp);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Failed onClick: " + e.toString());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        gCodeInputView.setText("");
        getHostInfo(); // get licenseKey, hostId and licenseCount
    }
}
