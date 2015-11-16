package Myba.utils;

import Server.Adresse;
import com.vaadin.tapio.googlemaps.client.LatLon;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Mixmania on 22-05-15 at 19:43.
 */
public final class GeoCodingUtils
{
    private final static String GOOGLE_PUBLIC_API_KEY = "AIzaSyAxvbVwWSm6reeZqDDMkFqAVYo6OHgrYaA";

    private GeoCodingUtils()
    {
    }

    private static String encodeAddress(Adresse a)
    {
        return String.valueOf(a.getNumber()) + "+" + a.getStreet().replaceAll(" ", "+") + "+," + a.getCity().replaceAll(" ", "+") + "+," + a.getCodePostal() + "+," + a.getCountry();
    }

    public static Location getLocation(Adresse address)
    {
        return getLocation(encodeAddress(address));
    }

    public static Location getLocation(String encodedAddress)
    {
        try
        {
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + ParsingUtils.normalize(encodedAddress) + "&sensor=false&key=" + GOOGLE_PUBLIC_API_KEY);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) builder.append(line);

            in.close();

            return new Location(builder.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String encodeString(String phrase)
    {
        String[] words = phrase.trim().toLowerCase().split(" ");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < words.length; i++)
        {
            builder.append(words[i]);

            if (i < words.length - 1) builder.append("+,");
        }

        return builder.toString();
    }

    public static double distanceBetween(Location pos1, Location pos2)
    {
        double R = 6371;
        double dLat = deg2rad(pos2.getLatLon().getLat() - pos1.getLatLon().getLat());
        double dLon = deg2rad(pos2.getLatLon().getLon() - pos1.getLatLon().getLon());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(deg2rad(pos1.getLatLon().getLat())) * Math.cos(deg2rad(pos2.getLatLon().getLat())) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private static double deg2rad(double deg)
    {
        return deg * (Math.PI / 180);
    }

    public static class Location
    {
        private LatLon ll;
        private String country;
        private String fullAdr;

        public Location(String JSON)
        {
            try
            {
                JSONObject root = new JSONObject(JSON).getJSONArray("results").getJSONObject(0);

                //LatLon
                JSONObject latlon = root.getJSONObject("geometry").getJSONObject("location");
                ll = new LatLon(latlon.getDouble("lat"), latlon.getDouble("lng"));

                //Full Address
                fullAdr = root.getString("formatted_address");

                //Country
                country = fullAdr.substring(fullAdr.lastIndexOf(",") + 1).trim();
            }
            catch (JSONException ignored)
            {
            }
        }

        public LatLon getLatLon()
        {
            return ll;
        }

        public boolean isCountry()
        {
            return country.equals(fullAdr);
        }

        public String getCountry()
        {
            return country;
        }
    }
}
