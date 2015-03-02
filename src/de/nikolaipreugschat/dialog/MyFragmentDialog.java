package de.nikolaipreugschat.dialog;

import de.nikolaipreugschat.adapters.ParcelableScanresult;
import de.nikolaipreugschat.main.MainActivity;
import de.nikolaipreugschat.main.R;
import de.nikolaipreugschat.main.TargetActivity;
import de.nikolaipreugschat.reciver.MyBroadcastReciever;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyFragmentDialog extends DialogFragment {
	int num;
	Bundle infos;
	MainActivity activity;
	MyBroadcastReciever reciever;
	
	/*
	 * Du kannst mit dem DialogFragment FragmentTransactions ausführen...
	 * Das sollte die Kapazitäten die du benötigst übersteigen. Also
	 * ein normaler Dialog sollte es auch tuhen
	 */
	
	public static MyFragmentDialog newInstance(int which, Bundle infos) {
		MyFragmentDialog myFragmentDialog = new MyFragmentDialog();
		
		Bundle args = new Bundle();
		args.putInt("num" ,which);
		myFragmentDialog.setArguments(args);
		
		myFragmentDialog.infos = infos;
		
		return myFragmentDialog;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		num = getArguments().getInt("num");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.dialog_layout, container);
		
		Button cancel = (Button) view.findViewById(R.id.button_dialog_left);
		
		TextView networkID = (TextView) view.findViewById(R.id.dialog_bssid);
		TextView macAddress = (TextView) view.findViewById(R.id.dialog_mac_address);
		TextView signalStrength = (TextView) view.findViewById(R.id.dialog_signal);
		
		ParcelableScanresult resultParcel = (ParcelableScanresult) infos.getParcelable("wlan");
		
		ScanResult result = resultParcel.getResult();
		
		networkID.setText(result.SSID);
		macAddress.setText(result.BSSID);
		signalStrength.setText(WifiManager.calculateSignalLevel(result.level, 100)+"");
		
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
				Intent intent = new Intent(getActivity(),TargetActivity.class);
				intent.putExtras(infos);
				startActivity(intent);
				
				MyBroadcastReciever reciever = ((MainActivity) getActivity()).myReciever;
				
				getActivity().unregisterReceiver(reciever);
				getDialog().dismiss();
			}
		});
		
		
		return view;
	}
	
	
}
