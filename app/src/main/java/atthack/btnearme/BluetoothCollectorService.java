package atthack.btnearme;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.Context;

import lateralview.net.attm2xapiv2.main.M2XAPI;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class BluetoothCollectorService extends IntentService implements BluetoothAdapter.LeScanCallback {

    BluetoothAdapter BA = BluetoothAdapter.getDefaultAdapter();

    static Devices devices;


    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_START = "atthack.btnearme.action.START";
    private static final String ACTION_STOP = "atthack.btnearme.action.STOP";


    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionStart(Context context)
    {
        M2XAPI.initialize(context,"317ab1598022498193552239be9615eb");

        Intent intent = new Intent(context, BluetoothCollectorService.class);
        intent.setAction(ACTION_START);
        context.startService(intent);
        devices = new Devices();
        devices.setContext(context);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionStop(Context context) {
        Intent intent = new Intent(context, BluetoothCollectorService.class);
        intent.setAction(ACTION_STOP);
        context.stopService(intent);


    }

    public BluetoothCollectorService() {

        super("BluetoothCollectorService");



    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START.equals(action)) {

                handleActionStart();
            } else if (ACTION_STOP.equals(action)) {
                handleActionStop();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStart() {
        BA.startLeScan(this);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStop() {

        BA.stopLeScan(this);
    }

    // LeScanListener callback
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord)
    {

        devices.addDevice(device.toString());

    }
}
