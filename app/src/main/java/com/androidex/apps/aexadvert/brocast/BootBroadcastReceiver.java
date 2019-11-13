package com.androidex.apps.aexadvert.brocast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.androidex.apps.aexadvert.MainActivity;

public class BootBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "aexHome";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO 开机启动		
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){		
			//Intent sayHelloIntent = new Intent(context,DevicesListActivity.class);
			//sayHelloIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			//context.startActivity(sayHelloIntent);	
			//Log.v(TAG, String.format("Recive %s.\n",intent.getAction()));
            Intent dlIntent = new Intent(context,MainActivity.class);
            dlIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(dlIntent);
			return;
		}
	}
}

