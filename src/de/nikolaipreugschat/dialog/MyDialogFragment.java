package de.nikolaipreugschat.dialog;

import de.nikolaipreugschat.main.R;
import android.app.DialogFragment;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyDialogFragment extends DialogFragment {
	private ScanResult result;

	public static MyDialogFragment newInstance(ScanResult result) {		
		MyDialogFragment myFragmentDialog = new MyDialogFragment();
		
		myFragmentDialog.result = result;
		
		return myFragmentDialog;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.dialog_layout, container);
		
		Button cancel = (Button) view.findViewById(R.id.button_dialog_left);
		
		TextView networkID = (TextView) view.findViewById(R.id.dialog_bssid);
		TextView macAddress = (TextView) view.findViewById(R.id.dialog_mac_address);
		TextView signalStrength = (TextView) view.findViewById(R.id.dialog_signal);
		
		networkID.setText(result.SSID);
		macAddress.setText(result.BSSID);
		signalStrength.setText(WifiManager.calculateSignalLevel(result.level, 100) + "%");
		
		getDialog().setTitle("Network");
		getDialog().setCanceledOnTouchOutside(true);
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getDialog().dismiss();
			}
		});
		
		Button next = (Button) view.findViewById(R.id.button_dialog_right);
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/**
				 * TODO - implement fragment transaction
				 */
				getDialog().dismiss();
			}
		});
		
		
		return view;
	}
}
