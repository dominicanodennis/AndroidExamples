package de.nikolaipreugschat.adapters;

import java.util.List;

import de.nikolaipreugschat.main.R;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<ScanResult> {
	
	List<ScanResult> data;
	Context context;

	public MyListAdapter(Context context, int resource, List<ScanResult> data) {
		super(context, resource);
		this.context = context;
		this.data = data;
	}

	@Override
	public ScanResult getItem(int position) {
		return data.get(position);
	}

	@Override
	public int getPosition(ScanResult item) {
		return data.indexOf(item);
	}
	
	@Override
	public int getCount() {
		return data.size();
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public void setNewData(List<ScanResult> results) {
		data = results;
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = convertView;
		
		if (view==null) {
			view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_item, parent,false);
		}
		
		TextView textView = (TextView) view.findViewById(R.id.list_item_value);
		TextView textViewNetwork = (TextView) view.findViewById(R.id.list_item_key);
		
		textViewNetwork.setText(data.get(position).SSID);
		
		textView.setText(WifiManager.calculateSignalLevel(data.get(position).level,100)+"%");
		
		return view;
	}

}
