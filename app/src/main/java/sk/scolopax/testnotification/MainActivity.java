package sk.scolopax.testnotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {


    private Button btnNoti;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNoti = (Button) findViewById(R.id.btnNoti);


        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNoti();
            }
        });

    }



    private void newNoti()
    {
            NotificationCompat.Builder mBuilder =
                    (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Hello hello, this is notification")
                            .setContentText("Text of natification is simple.");

            Intent resultIntent = new Intent(this, MainActivity.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);

            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent( 0,   PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);


        android.support.v4.app.NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        String[] events = new String[6];
        events[0] = "Perer";
        events[1] = "Matej";
        events[2] = "kkujira";
        events[3] = "asap";
        events[4] = "loki";
        events[5] = "tom";

       inboxStyle.setBigContentTitle("Event tracker details:");

        for (int i=0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }

        mBuilder.setStyle(inboxStyle);




             Notification noti = mBuilder.build();
                 noti.flags = Notification.FLAG_AUTO_CANCEL;

            NotificationManager mNotificationManager =  (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(3, noti);
    }

}
