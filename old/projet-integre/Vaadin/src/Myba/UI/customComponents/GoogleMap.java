package Myba.UI.customComponents;

import Myba.utils.GeoCodingUtils;
import Server.Adresse;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mixmania on 22-05-15 at 19:21.
 */

public class GoogleMap extends com.vaadin.tapio.googlemaps.GoogleMap
{
    private static final String GOOGLE_PUBLIC_API_KEY = "AIzaSyAxvbVwWSm6reeZqDDMkFqAVYo6OHgrYaA";
    private final List<GoogleMapMarker> markers = new ArrayList<>();

    public GoogleMap(double lat, double lng, int zoom)
    {
        super(new LatLon(lat, lng), zoom, GOOGLE_PUBLIC_API_KEY);
    }

    public void clearMarkers()
    {
        markers.forEach(this::removeMarker);
        markers.clear();
    }

    @Override
    public GoogleMapMarker addMarker(String caption, LatLon position, boolean draggable, String iconUrl)
    {
        GoogleMapMarker t = super.addMarker(caption, position, draggable, iconUrl);
        this.markers.add(t);
        return t;
    }

    @Override
    public void addMarker(GoogleMapMarker marker)
    {
        super.addMarker(marker);
        this.markers.add(marker);
    }

    public GoogleMapMarker addMarker(Adresse address)
    {
        return this.addMarker(null, GeoCodingUtils.getLocation(address).getLatLon(), false, null);
    }

    public GoogleMapMarker addMarker(String caption, Adresse address, boolean dragabble, String url)
    {
        return this.addMarker(caption, GeoCodingUtils.getLocation(address).getLatLon(), dragabble, url);
    }

    public void focusLocation(GeoCodingUtils.Location location)
    {
        if (location.isCountry())
        {
            this.setZoom(6);
            this.setCenter(location.getLatLon());
        } else
        {
            this.setZoom(11);
            this.setCenter(location.getLatLon());
        }
    }
}
