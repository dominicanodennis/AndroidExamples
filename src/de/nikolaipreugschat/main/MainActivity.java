package de.nikolaipreugschat.main;

import de.nikolaipreugschat.adapters.MyListAdapter;
import de.nikolaipreugschat.fragments.MyListFragment;
import de.nikolaipreugschat.reciver.MyBroadcastReciever;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
	
	int activeFragment = 0;
	MyListFragment myListFragment;
	
	WifiManager.WifiLock lock;
	public WifiManager wifiManager;
	public MyBroadcastReciever myReciever;
	MyListAdapter adapter;
	FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_base_layout);
        
        fragmentManager = getSupportFragmentManager();
        
        MyListFragment myListFragment = (MyListFragment) fragmentManager.findFragmentById(R.id.my_list_fragment);
        
        adapter = myListFragment.list;
        
        myReciever = new MyBroadcastReciever(myListFragment, this);
        
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    	
    	registerReceiver(myReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

    	wifiManager.startScan();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void switchToTracking() {
    	
    }
    
    @Override
    public void onStop() {
    //	lock.release();
    }
}
