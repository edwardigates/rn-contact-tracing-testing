package moh.gov.il.specialble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import moh.gov.il.specialble.util.PrefUtils;

/**
 * Currently filtering in only BOOT_COMPLETE and MY_PACKEGE_REPLACED.
 * NOTE: MY_PACKAGE_REPLACED was added after PACKAGE_REPLACED and is meant to prevent waking the app up every time any app is replaced on device.
 */
public class BLEReceiver extends BroadcastReceiver {
    private static final String TAG = BLEReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent arg1) {

        if(PrefUtils.getStartServiceValue(context)) {
            BLEForegroundService.startThisService(context);
            Log.d(TAG, "Restarting BLE service..");
        }
    }
}