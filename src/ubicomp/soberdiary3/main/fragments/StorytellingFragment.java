package ubicomp.soberdiary3.main.fragments;

import ubicomp.soberdiary3.R;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StorytellingFragment extends ListFragment {

	private View view;
	//private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//listView = getListView();
		 String[] arr = new String[]{
	             "A","B","C","D","E","F","G"
	     };
	     ArrayAdapter<String> adapter = 
	            new ArrayAdapter<String>(getActivity(),
	                android.R.layout.simple_list_item_2,arr);
	     setListAdapter(adapter);
	        


	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.fragment_3, container,
				false);	

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		//ClickLog.Log(ClickLogId.STORYTELLING_ENTER);
		//getView().setFocusableInTouchMode(true);
		//getView().requestFocus();

		//RecordCheckTask task = new RecordCheckTask();
		//task.execute();
	}
}




