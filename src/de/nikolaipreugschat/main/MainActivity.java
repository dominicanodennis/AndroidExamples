package de.nikolaipreugschat.main;

import java.util.ArrayList;
import java.util.List;

import de.nikolaipreugschat.dialog.MyFragmentDialog;
import de.nikolaipreugschat.fragments.MyListAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        ListView listView = (ListView) findViewById(R.id.list);
        
        List<String> dummy = new ArrayList<String>();
        
        for (int i=0;i<50;i++) {
        	dummy.add(""+i);
        }
        
        listView.setAdapter(new MyListAdapter(getApplicationContext(), R.layout.list_item, dummy));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyListAdapter list = (MyListAdapter) parent.getAdapter();
				
				String item = list.getItem(position);
				
				Bundle bundle = new Bundle();
				bundle.putString("inputValue", item);
				
				MyFragmentDialog myFragmentDialog = MyFragmentDialog.newInstance(1, bundle);
				myFragmentDialog.show(getFragmentManager(), "");
			}
		});
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
