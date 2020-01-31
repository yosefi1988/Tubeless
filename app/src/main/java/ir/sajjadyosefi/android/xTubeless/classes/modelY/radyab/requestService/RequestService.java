package ir.sajjadyosefi.android.xTubeless.classes.model.radyab.requestService;

/**
 * Created by sajjad on 6/4/2018.
 */

public class RequestService {
    public static int SERVICE_TYPE      = 0;
    public static int SERVICE_GEO       = 1;
    public static int BATTERY_LEVEL     = 2;
    public static int SERVICE_ADDRESS     = 3;
    public static int SERVICE_GEO_NETWORK       = 4;



    int ServiceType ;


    public int getServiceType() {
        return ServiceType;
    }

    public void setServiceType(int serviceType) {
        ServiceType = serviceType;
    }
}
