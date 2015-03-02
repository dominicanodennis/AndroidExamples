package de.nikolaipreugschat.main;

import de.nikolaipreugschat.adapters.ParcelableScanresult;
import de.nikolaipreugschat.reciver.MyBroadcastReciever;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class TargetActivity extends ActionBarActivity {
	
	MyBroadcastReciever reciever;
	WifiManager wifiManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.target_layout);		
		
		Bundle bundle = getIntent().getExtras();
		
		ParcelableScanresult parcel = bundle.getParcelable("wlan");
		
		reciever = new MyBroadcastReciever(getCurrentFocus(), this, parcel.getResult());
		
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		
		reciever.goAsync();
		
		registerReceiver(reciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		wifiManager.startScan();
	}

}
