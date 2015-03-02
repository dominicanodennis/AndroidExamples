package de.nikolaipreugschat.main;

import java.util.ArrayList;
import java.util.List;

import de.nikolaipreugschat.adapters.MyListAdapter;
import de.nikolaipreugschat.adapters.ParcelableScanresult;
import de.nikolaipreugschat.dialog.MyFragmentDialog;
import de.nikolaipreugschat.reciver.MyBroadcastReciever;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	
	WifiManager wifiManager;
	public MyBroadcastReciever myReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        ListView listView = (ListView) findViewById(R.id.list);
        
        List<ScanResult> dummy = new ArrayList<ScanResult>();
                
        listView.setAdapter(new MyListAdapter(getApplicationContext(), R.layout.list_item, dummy));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyListAdapter list = (MyListAdapter) parent.getAdapter();
				
				ScanResult item = list.getItem(position);
				
				ParcelableScanresult parcel = new ParcelableScanresult(item);

				Bundle bundle = new Bundle();
				bundle.putParcelable("wlan", parcel);
				
				MyFragmentDialog myFragmentDialog = MyFragmentDialog.newInstance(1, bundle);
				myFragmentDialog.show(getFragmentManager(), "");
			}
		});
        
        myReciever = new MyBroadcastReciever((MyListAdapter) listView.getAdapter(), this);
        
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        
        myReciever.goAsync();
        
        registerReceiver(myReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		wifiManager.startScan();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
