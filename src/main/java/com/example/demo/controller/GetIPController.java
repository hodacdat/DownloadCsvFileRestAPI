package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
public class GetIPController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/user/ip")
    public String getUserIP() {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
    @GetMapping("/get-ip")
    public String getClientIP(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        return "Client IP Address: " + ipAddress;
    }

    @GetMapping("/ip-info")
    public String getIPInfo(HttpServletRequest request) {
        StringBuilder response = new StringBuilder();

        // Lấy thông tin IP
        String ipAddress = request.getHeader("x-real-ip");
        response.append("IP Address: ").append(ipAddress).append("\n");

        // Lấy thông tin quốc gia
        String country = request.getHeader("ip-country");
        response.append("Country: ").append(country).append("\n");

        // Lấy thông tin vùng/quận
        String region = request.getHeader("ip-country-region");
        response.append("Region: ").append(region).append("\n");

        // Lấy thông tin thành phố
        String city = request.getHeader("ip-city");
        response.append("City: ").append(city).append("\n");

        // Lấy thông tin vĩ độ
        String latitude = request.getHeader("ip-latitude");
        response.append("Latitude: ").append(latitude).append("\n");

        // Lấy thông tin kinh độ
        String longitude = request.getHeader("ip-longitude");
        response.append("Longitude: ").append(longitude).append("\n");

        // Lấy thông tin múi giờ
        String timezone = request.getHeader("ip-timezone");
        response.append("Timezone: ").append(timezone).append("\n");

        return response.toString();
    }

    @GetMapping("/ip-info1")
    public String getIPInfo1(HttpServletRequest request) {
        StringBuilder response = new StringBuilder();

        // In ra tất cả các header của request
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            response.append(headerName).append(": ").append(request.getHeader(headerName)).append("\n");
        }

        return response.toString();
    }

    @GetMapping("/ipv6")
    public String getIpV6(HttpServletRequest request) {
        // Try to get the IPv6 address from the headers
        String ipv6Address = request.getHeader("X-Forwarded-For");

        // If the headers don't contain the IPv6 address, use the remote address
        if (ipv6Address == null || ipv6Address.isEmpty() || "unknown".equalsIgnoreCase(ipv6Address)) {
            ipv6Address = request.getRemoteAddr();
        }

        // If there are multiple IP addresses, use the first one
        if (ipv6Address != null && ipv6Address.contains(",")) {
            ipv6Address = ipv6Address.split(",")[0].trim();
        }

        return ipv6Address;
    }


    @GetMapping("/user-info")
    public ResponseEntity<String> getUserInfo(HttpServletRequest request) {
        String forwardedProto = request.getHeader("x-forwarded-proto");
        String forwardedFor = request.getHeader("x-forwarded-for");
        String realIp = request.getHeader("x-real-ip");
        String country = request.getHeader("ip-country");
        String countryRegion = request.getHeader("ip-country-region");
        String city = request.getHeader("ip-city");
        String latitude = request.getHeader("ip-latitude");
        String longitude = request.getHeader("ip-longitude");
        String timezone = request.getHeader("ip-timezone");

        StringBuilder response = new StringBuilder();
        response.append("x-forwarded-proto: ").append(forwardedProto).append("\n");
        response.append("x-forwarded-for: ").append(forwardedFor).append("\n");
        response.append("x-real-ip: ").append(realIp).append("\n");
        response.append("ip-country: ").append(country).append("\n");
        response.append("ip-country-region: ").append(countryRegion).append("\n");
        response.append("ip-city: ").append(city).append("\n");
        response.append("ip-latitude: ").append(latitude).append("\n");
        response.append("ip-longitude: ").append(longitude).append("\n");
        response.append("ip-timezone: ").append(timezone);

        return ResponseEntity.ok(response.toString());
    }
}
