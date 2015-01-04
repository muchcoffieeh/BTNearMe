package atthack.btnearme;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;
import android.content.Context;
import android.util.Log;


import lateralview.net.attm2xapiv2.listeners.ResponseListener;
import lateralview.net.attm2xapiv2.model.Device;
import lateralview.net.attm2xapiv2.network.ApiV2Response;

/**
 * Created by jd0507 on 1/4/15.
 */
public class Devices implements ResponseListener
{

    static final int BUFFER_SIZE = 30;
    List<String> deviceList = new ArrayList<String>();
    Context context;


    static List<String> whiteListDevices;


    public void Devices()
    {
        whiteListDevices = new ArrayList();
        whiteListDevices.add("B0-D0-9C-09-D0-F9");
        whiteListDevices.add("D8-BB-2C-06-AC-9B");
        whiteListDevices.add("B8-E8-56-46-DF-97");
        whiteListDevices.add("28-BA-B5-F0-0C-4B");

    }

    public void setContext(Context context)
    {
        this.context = context;
    }
    public void addDevice(String deviceName)
    {

        if (whiteListDevices.contains(deviceName))
        {

            Log.d("DEVICE", "found whitelisted device " + deviceName);
            try {
                JSONObject obj = new JSONObject("{\"value\":\"" + deviceName + "\"}");

                Device.updateDataStreamValue(
                        context,
                        obj,
                        "802f1824496d5ccd64eef6783e915b6b",
                        "bluetoothuuid",
                        this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadDevices(List<String> deviceList)
    {
 /*       try {
            StringBuffer sb = new StringBuffer();

            boolean first = true;

            for (String s : deviceList)
            {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }

                sb.append("{ \"value\" : \"" + s + "\" }");
            }
            sb.append("] }");

            Log.d("Devices; ", sb.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
 */   }

    // ATT M2X API ResponseListener interface
    public void onRequestCompleted(ApiV2Response result, int requestCode)
    {
        Log.d("ResponseListener", "onRequestCompleted()");
    }

    // ATT M2X API ResponseListener interface
    public void onRequestError(ApiV2Response error, int requestCode)
    {
        Log.d("ResponseListener", "onRequestError() " + error.get_raw());

    }
}
