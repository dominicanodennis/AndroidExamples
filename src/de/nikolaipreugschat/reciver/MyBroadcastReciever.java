package de.nikolaipreugschat.reciver;

import java.util.List;

import de.nikolaipreugschat.adapters.MyListAdapter;
import de.nikolaipreugschat.main.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyBroadcastReciever extends BroadcastReceiver {
	
	MyListAdapter listAdapter; // Listenadapter in den die Liste übergeben wird
	WifiManager wifiManager;
	ActionBarActivity activity; // activity um das scope in die Klasse zu überreichen
	View view;
	ScanResult result;
	
	public MyBroadcastReciever(MyListAdapter listAdapter, ActionBarActivity activity) {
		this.listAdapter = listAdapter;
		this.activity = activity;
		wifiManager = (WifiManager) this.activity.getSystemService(Context.WIFI_SERVICE);
	}
	
	public MyBroadcastReciever(View view, ActionBarActivity activity, ScanResult result) {
		this.view = view;
		this.activity = activity;
		wifiManager = (WifiManager) this.activity.getSystemService(Context.WIFI_SERVICE);
		this.result = result;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if (!(listAdapter==null)) {
			List<ScanResult> results = wifiManager.getScanResults();
			
			listAdapter.setNewData(results);
		} else {
			List<ScanResult> results = wifiManager.getScanResults();
			ScanResult tracked = null;
			
			for (ScanResult res: results) {
				if (res.BSSID.equals(this.result.BSSID)) {
					tracked = res;
				}
			}
			
			TextView textView = (TextView) activity.findViewById(R.id.target_data_output);
			TextView textView2 = (TextView) activity.findViewById(R.id.target_network);
			if (tracked!=null) { 
				textView2.setText(tracked.SSID);
				textView.setText("Signal Strength: " + WifiManager.calculateSignalLevel(tracked.level, 100) + "%");
			} else {
				Toast.makeText(context, "tracked==null", Toast.LENGTH_SHORT).show();
			}
		}
	}

}
