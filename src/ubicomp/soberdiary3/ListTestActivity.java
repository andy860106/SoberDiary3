package ubicomp.soberdiary3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListTestActivity extends Activity {

	private static final String[] strs = new String[] {
		    "first", "second", "third", "fourth", "fifth"
	}; // 
	private ListView lv; /** Called when the activity is first created. */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_test);
		
		lv = (ListView) findViewById(R.id.listView1);  
		
		lv.setAdapter( new ArrayAdapter<String>( this ,
                android.R.layout.simple_list_item_checked, strs));
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

	}
	
}
