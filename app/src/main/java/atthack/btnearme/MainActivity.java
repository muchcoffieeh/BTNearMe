package atthack.btnearme;

import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.util.Log;


import lateralview.net.attm2xapiv2.main.M2XAPI;
import lateralview.net.attm2xapiv2.model.Device;


import org.json.JSONObject;
import org.json.JSONException;


public class MainActivity extends Activity
{

    private Button On,Off,startScan,stopScan,upload;

    private BluetoothAdapter BA;
    private Set<BluetoothDevice>pairedDevices;
    private ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        On = (Button)findViewById(R.id.onButton);
        Off = (Button)findViewById(R.id.offButton);
        startScan = (Button)findViewById(R.id.startScanButton);
        stopScan = (Button)findViewById(R.id.stopScanButton);
        upload = (Button)findViewById(R.id.uploadButton);

        lv = (ListView)findViewById(R.id.listView1);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }





    public void on(View view){
        if (BA == null) return;

        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(),"Turned on"
                    ,Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Already on",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void startScan(View view)
    {

       BluetoothCollectorService.startActionStart(getApplicationContext());
    }

    public void stopScan(View view)
    {
        BluetoothCollectorService.startActionStop(getApplicationContext());
    }


    public void off(View view){
        BA.disable();
        Toast.makeText(getApplicationContext(),"Turned off" ,
                Toast.LENGTH_LONG).show();
    }



    public void visible(View view){
        Intent getVisible = new Intent(BluetoothAdapter.
                ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);

    }






}
