package com.dimeno.commons.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dimeno.commons.ui.AppUtils;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * network util
 * Created by wangzhen on 2020/8/19.
 */
public class NetUtils {

    public static boolean isAvailable() {
        NetworkInfo networkInfo = getNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isMobile() {
        NetworkInfo info = getNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return false;
        }
        return ConnectivityManager.TYPE_MOBILE == info.getType();
    }

    public static boolean isWifi() {
        NetworkInfo info = getNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return false;
        }
        return ConnectivityManager.TYPE_WIFI == info.getType();
    }

    private static NetworkInfo getNetworkInfo() {
        ConnectivityManager cm = (ConnectivityManager) AppUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public static String getHostIP() {
        String hostIp = null;
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            InetAddress inetAddress = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    inetAddress = ias.nextElement();
                    if (inetAddress instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = inetAddress.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = inetAddress.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return hostIp;
    }


}
