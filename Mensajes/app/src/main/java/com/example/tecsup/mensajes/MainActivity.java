package com.example.tecsup.mensajes;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
        implements OnClickListener {

    String strPhone = "XXXXXXXXXXX";
    String strMessage = "LoremnIpsum";

    Button btnAll;
    Button btnInbox;
    Button btnSent;
    Button btnDraft;
    TableLayout tblMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setType("vnd.android-dir/mms-sms");
        sendIntent.putExtra("address", strPhone);
        sendIntent.putExtra("sms_body", strMessage);
        startActivity(sendIntent);

        strPhone = "XXXXXXXXXXX";
        strMessage = "LoremnIpsum";

        Uri sms_uri = Uri.parse("smsto:+" + strPhone);
        Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
        sms_intent.putExtra("sms_body", txtMessage.getText().toString());
        startActivity(sms_intent);

        PackageManager pm = this.getPackageManager();

        if (!pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY) &&
                !pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA)) {
            Toast.makeText(this, "Lo sentimos, tu dispositivo probablemente no pueda enviar SMS...", Toast.LENGTH_SHORT).show();
        }

        try {
            /*
             * Initializing Widgets
             */

            btnAll = (Button) findViewById(R.id.btnAll);
            btnAll.setOnClickListener(this);

            btnInbox = (Button) findViewById(R.id.btnInbox);
            btnInbox.setOnClickListener(this);

            btnSent = (Button) findViewById(R.id.btnSent);
            btnSent.setOnClickListener(this);

            btnDraft = (Button) findViewById(R.id.btnDraft);
            btnDraft.setOnClickListener(this);

            tblMain = (TableLayout) findViewById(R.id.tblMain);

        } catch (Exception ex) {
            Toast.makeText(this,
                    "Error in MainActivity.onCreate: " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Uri smsUri = Uri.parse("content://sms/");

        switch (v.getId()) {
            case R.id.btnInbox:
                smsUri = Uri.parse("content://sms/inbox");
                break;
            case R.id.btnSent:
                smsUri = Uri.parse("content://sms/sent");
                break;
            case R.id.btnDraft:
                smsUri = Uri.parse("content://sms/draft");
                break;
        }

        Cursor cursor = getContentResolver().query(smsUri, null, null, null, null);

        Cursor2TableLayout(cursor, tblMain);
    }

    public void Cursor2TableLayout(Cursor cur, TableLayout tblLayout) {

        /* Clearing Table If Contains Any Rows/Headers */
        tblLayout.removeAllViews();

        /* Moving To First */
        if (!cur.moveToFirst()) { /* false = cursor is empty */
            return;
        }

        /* Column Headers */

        TableRow headersRow = new TableRow(this);

        for (int j = 0; j < cur.getColumnCount(); j++) {
            TextView textView = new TextView(this);

            textView.setGravity(Gravity.CENTER_HORIZONTAL);

            textView.setText(cur.getColumnName(j));

            textView.setPadding(0, 0, 5, 0);

            textView.setAlpha(0.8f);

            headersRow.addView(textView);
        }

        headersRow.setPadding(10, 10, 10, 10);

        tblLayout.addView(headersRow);

        /* Rows */

        for (int i = 0; i < cur.getCount(); i++) {

            TableRow tableRow = new TableRow(this);

            for (int j = 0; j < cur.getColumnCount(); j++) {
                TextView textView = new TextView(this);

                textView.setGravity(Gravity.CENTER_HORIZONTAL);

                textView.setText(cur.getString(j));

                textView.setPadding(0, 0, 5, 0);

                tableRow.addView(textView);
            }

            tableRow.setPadding(10, 10, 10, 10);

            tblLayout.addView(tableRow);
            cur.moveToNext();
        }

        cur.close();
    }
}

