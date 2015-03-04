package de.nikolaipreugschat.reciver;

import java.util.List;

import de.nikolaipreugschat.adapters.MyListAdapter;
import de.nikolaipreugschat.fragments.MyListFragment;
import de.nikolaipreugschat.fragments.WifiTrackingFragment;
import de.nikolaipreugschat.main.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyBroadcastReciever extends BroadcastReceiver {
	
	MyListAdapter listAdapter;
	WifiManager wifiManager;
	ActionBarActivity activity;
	View view;
	ScanResult result;
	MyListFragment list;
	WifiTrackingFragment trackingFragment;
	
	public MyBroadcastReciever(MyListFragment listFragment, ActionBarActivity activity) {
		this.list = listFragment;
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
		Toast.makeText(context, "recieved", Toast.LENGTH_SHORT).show();
		if (list!=null) {
			if (list.isCreated()) {
				MyListAdapter myAdapter = (MyListAdapter) list.listView.getAdapter();
				
				myAdapter.setNewData(wifiManager.getScanResults());
			}
			
		} else if (trackingFragment!=null) {
			if (trackingFragment.isCreated()) {
				
			}
			
		} else {
			Toast.makeText(context, "recieved but not ready", Toast.LENGTH_SHORT).show();
		}
		
	}

}
