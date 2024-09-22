package com.mjrt.terminal.localchat.util;

import org.jetbrains.annotations.Nullable;

import java.net.NetworkInterface;
import java.net.SocketException;

public class IpManager {
    public static @Nullable String getInetAddress() {
        try {
            for (var en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                var networkInterface = en.nextElement();
                if (networkInterface.getName().contains("wlan") || networkInterface.getName().contains("ap")) {
                    for (var enumInetAddresses = networkInterface.getInetAddresses(); enumInetAddresses.hasMoreElements(); ) {
                        var inetAddress = enumInetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && inetAddress.getAddress().length == 4)
                            return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }
}
