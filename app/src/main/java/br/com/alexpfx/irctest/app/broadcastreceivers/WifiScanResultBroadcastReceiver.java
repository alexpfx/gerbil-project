package br.com.alexpfx.irctest.app.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.Toast;
import br.com.alexpfx.irctest.app.BusProvider;
import br.com.alexpfx.irctest.app.WifiInfo;
import br.com.alexpfx.irctest.app.WifiList;
import br.com.alexpfx.irctest.app.mvp.model.irc.WifiReceivedEvent;
import com.squareup.otto.Bus;

import java.util.List;

/**
 * Created by alexandre on 27/06/15.
 */
public class WifiScanResultBroadcastReceiver extends BroadcastReceiver {

    private Bus bus = BusProvider.INSTANCE.get();

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
        final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        final List<ScanResult> scanResults = wifiManager.getScanResults();
        WifiList list = new WifiList();
        for (ScanResult scanResult : scanResults) {
            WifiInfo wifiInfo = WifiInfo.newInstance(scanResult.BSSID, scanResult.SSID, scanResult.level);
            list.add(wifiInfo);
        }
        bus.post(new WifiReceivedEvent(list));
    }


}
