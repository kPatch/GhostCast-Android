package st.ghostca.ghostcast.datastore;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by uwe on 11/30/14.
 */
//user location
public class Location {

    private final String NAME = "Location";
    private Date timestamp;
    private ParseObject locationObject;
    private ParseGeoPoint geoPoint;

    public Location(double longitude, double latitude) {
        geoPoint = new ParseGeoPoint(latitude, longitude);
        locationObject.put("geolocation", geoPoint);
    }

    public void Save() {
        locationObject.saveInBackground();
    }


}
