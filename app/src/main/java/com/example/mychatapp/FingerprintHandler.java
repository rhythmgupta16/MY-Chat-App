package com.example.mychatapp;


import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.TextView;
import android.widget.Toast;


public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private TextView tv;


    public FingerprintHandler(TextView tv) {
        this.tv = tv;
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        tv.setText("Auth error");
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);

        Toast.makeText(Context.getContext(), "Authentication succeeded.", Toast.LENGTH_LONG).show();


        Intent intent = new Intent(Context.getContext(),
                GetNumber.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Context.getContext().startActivity(intent);
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
    }

    public void doAuth(FingerprintManager manager, FingerprintManager.CryptoObject obj) {
        CancellationSignal signal = new CancellationSignal();

        try {
            manager.authenticate(obj, signal, 0, this, null);
        } catch (SecurityException sce) {
        }
    }
}