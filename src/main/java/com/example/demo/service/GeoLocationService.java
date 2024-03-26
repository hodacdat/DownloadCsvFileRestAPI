package com.example.demo.service;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class GeoLocationService {

    private final DatabaseReader reader;

    public GeoLocationService() throws IOException {
        // Load the GeoLite2 City database file
        File database = new File("src/main/resources/GeoLite2-City.mmdb");
        this.reader = new DatabaseReader.Builder(database).build();
    }

    public String getGeoLocationInfo(String ipAddress) throws IOException, GeoIp2Exception {
        InetAddress ip = InetAddress.getByName(ipAddress);
        CityResponse response = reader.city(ip);

        // Construct and return information
        return "Country: " + response.getCountry().getName() +
                ", City: " + response.getCity().getName() +
                ", Latitude: " + response.getLocation().getLatitude() +
                ", Longitude: " + response.getLocation().getLongitude();
    }
}
