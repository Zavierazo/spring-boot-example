package com.example.util;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Scanner;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

    public static double distance(double lat1, double lat2, double lon1, double lon2) {
        return distance(lat1, lat2, lon1, lon2, 0, 0);
    }

    public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {
        final int R = 6371; // Radius of the earth
        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        double height = el1 - el2;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }

    public static void ignoreSSL() {
        SSLContext ctx = null;
        TrustManager[] trustAllCerts = new X509TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };
        try {
            ctx = SSLContext.getInstance("SSL");
            ctx.init(null, trustAllCerts, null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.info("Error loading ssl context ", e);
        }
        SSLContext.setDefault(ctx);
    }

    public static String readFile(ClassLoader classLoader, String filePath) {
        File file = new File(classLoader.getResource(filePath).getFile());
        StringBuilder defaultData = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                defaultData.append(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            log.error("Error reading file", e);
        }
        return defaultData.toString();
    }

    public static String readFile(File file) {
        StringBuilder defaultData = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                defaultData.append(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            log.error("Error reading file", e);
        }
        return defaultData.toString();
    }
}
