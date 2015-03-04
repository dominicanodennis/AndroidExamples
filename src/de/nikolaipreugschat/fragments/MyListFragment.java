package de.nikolaipreugschat.fragments;

import java.util.ArrayList;
import java.util.List;

import de.nikolaipreugschat.adapters.MyListAdapter;
import de.nikolaipreugschat.dialog.MyDialogFragment;
import de.nikolaipreugschat.main.R;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class MyListFragment extends Fragment {
	public ListView listView;
	private boolean created; // to prevent NullPointerExceptions
	public MyListAdapter list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.list_fragment_layout, container);
		
		listView = (ListView) view.findViewById(R.id.list);
		
		List<ScanResult> dummy = new ArrayList<ScanResult>();
        
        listView.setAdapter(new MyListAdapter(getActivity().getApplicationContext(), R.layout.list_item, dummy));
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				list = (MyListAdapter) parent.getAdapter();
				
				ScanResult item = list.getItem(position);

				MyDialogFragment myFragmentDialog = MyDialogFragment.newInstance(item);
				myFragmentDialog.show(getActivity().getFragmentManager(), "");
			}
		});
		
		
		
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
	}
	
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		created = true;
	}

	public boolean isCreated() {
		return created;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}	
	
}
