package com.example.android.notifiactionsdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_NOTIFICATION_REPLY = 12;
    private int NOTIFY_HELLO = 10;
    private int REQUEST_NOTIFICATION = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //delete all existing notifications.
        NotificationManagerCompat.from(this).cancel(NOTIFY_HELLO);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });
    }

    private void showNotification() {
        //1) NotificationCompat.Builder - build the notification object
        //AlertDialog.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        /*
        //init a big text style
        NotificationCompat.BigTextStyle textStyle = new android.support.v4.app.NotificationCompat.BigTextStyle();

        textStyle.bigText(getString(R.string.big_text))
                .setBigContentTitle(getString(R.string.title_when_open))
                .setSummaryText(getString(R.string.short_summary));

        */

        //init a bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android_n);
        //init the style

        NotificationCompat.BigPictureStyle bigPictureStyle = new android.support.v4.app.NotificationCompat.BigPictureStyle();
        //set properties of the style
        bigPictureStyle.bigPicture(bitmap)
                .bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_dismiss))
                .setBigContentTitle("The Big title")
                .setSummaryText("Summary Text");



        builder.setContentTitle(getString(R.string.notification_title)).
                setContentText(getString(R.string.content_text)).
                setSmallIcon(R.mipmap.ic_launcher).
                setContentIntent(getPendingIntent()).
                setStyle(bigPictureStyle).
                //addAction(R.drawable.ic_reply, getString(R.string.action_reply), getPendingIntentReply()).
                //addAction(R.drawable.ic_dismiss, getString(R.string.dismiss_action), getPendingIntent()).
                //addAction(R.drawable.ic_stat_name, getString(R.string.close_action), getPendingIntent()).
        //        setStyle(textStyle).
                setAutoCancel(true);

        //done building the notification
        Notification notification = builder.build();

        //2) reference to NotificationManager - notify
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        //3) dispatch the notification:
        notificationManager.notify(NOTIFY_HELLO, notification);
    }

    public PendingIntent getPendingIntent() {
        //first we create an intent
        Intent intent = new Intent(this, MainActivity.class);
        //get Activity - creates or updates an existing Pending Intent
        return PendingIntent.getActivity(this,
                REQUEST_NOTIFICATION,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }


    public PendingIntent getPendingIntentReply() {
        //first we create an intent
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ReplyID", 101);
        intent.putExtra("sender", 101);
        //get Activity - creates or updates an existing Pending Intent
        return PendingIntent.getActivity(this,
                REQUEST_NOTIFICATION_REPLY,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
