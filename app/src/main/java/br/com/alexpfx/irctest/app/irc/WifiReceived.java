package br.com.alexpfx.irctest.app.irc;

import br.com.alexpfx.irctest.app.WifiList;

/**
 * Created by alex on 12/07/2015.
 */
public class WifiReceived {

    private WifiList wifiList;

    public WifiReceived(WifiList wifiList) {
        this.wifiList = wifiList;
    }

    @Override
    public String toString() {
        return wifiList.toString();
    }
}
