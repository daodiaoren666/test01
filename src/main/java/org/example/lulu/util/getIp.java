package org.example.lulu.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class getIp {

    public  String getTraditionIp() throws  UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        return hostAddress;
    }

}
