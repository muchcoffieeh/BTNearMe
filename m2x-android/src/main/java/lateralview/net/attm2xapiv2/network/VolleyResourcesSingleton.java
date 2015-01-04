package lateralview.net.attm2xapiv2.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Joaquin on 1/12/14.
 */
public class VolleyResourcesSingleton {

    private static VolleyResourcesSingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private VolleyResourcesSingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyResourcesSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyResourcesSingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
