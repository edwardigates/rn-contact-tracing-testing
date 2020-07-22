package moh.gov.il.specialble;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import moh.gov.il.specialble.bt.Device;
import moh.gov.il.specialble.bt.Scan;
import moh.gov.il.specialble.listeners.IEventListener;

public class EventToJSDispatcher implements IEventListener {
    ReactApplicationContext context;
    static EventToJSDispatcher sEventDispatcher;
    private static final String TAG = EventToJSDispatcher.class.getSimpleName();

    private EventToJSDispatcher(ReactApplicationContext context) {
        this.context = context;
    }

    public static EventToJSDispatcher getInstance(ReactApplicationContext context) {
        if (sEventDispatcher == null) {
            sEventDispatcher = new EventToJSDispatcher(context);
        }
        return sEventDispatcher;
    }

    private void dispatch(@NonNull String eventName, @Nullable Object data) {
        try {
            context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, data);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onEvent(String event, Object data) {
        if (data instanceof Boolean) {
            dispatch(event, toBoolean((Boolean) data));
        }
        else if (data==null || data instanceof WritableMap) {
            dispatch(event, data);
        }
        else {
            Log.i(TAG, "onEvent | Data object for event ["+event+"] must be boolean or WriteableMap !");
        }

    }

    // returns true or false if false or null
    private boolean toBoolean(Boolean bool) {
        return bool != null && bool;
    }

    public void sendAdvertisingStatus(boolean status) {
        dispatch("advertisingStatus",status);
    }

    public void sendScanningStatus(boolean status) {
        dispatch("scanningStatus",status);
    }

    public void sendNewDevice(Device newDevice) {
        WritableMap params = newDevice.toWritableMap();
        dispatch("foundDevice",params);
    }

    public void sendNewScan(Scan newScan) {
        WritableMap params = newScan.toWritableMap();
        dispatch("foundScan",params);
    }
}
