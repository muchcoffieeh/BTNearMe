package lateralview.net.attm2xapiv2.listeners;

import java.util.List;

import lateralview.net.attm2xapiv2.network.ApiV2Response;

/**
 * Created by Joaquin on 1/12/14.
 */
public interface ResponseListener <T> {

    /**
     * Invoked when the request has completed its execution without errors
     * @param result The resulting object from the AsyncTask.
     */
    public void onRequestCompleted(ApiV2Response result, int requestCode);


    /**
     * Invoked when the Request has completed its execution with errors
     * @param error The error message.
     */
    public void onRequestError(ApiV2Response error, int requestCode);

}
