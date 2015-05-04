package ubicomp.soberdiary3;

import java.io.File;
import java.util.List;

import ubicomp.soberdiary3.main.ui.toast.CustomToast;
import ubicomp.soberdiary3.main.ui.toast.CustomToastSmall;
import ubicomp.soberdiary3.test.data.BracValueDebugHandler;
import ubicomp.soberdiary3.test.data.BracValueFileHandler;
import ubicomp.soberdiary3.test.data.ImageFileHandler;
import ubicomp.soberdiary3.test.data.QuestionFile;
import ubicomp.soberdiary3.test.ui.TestQuestionCaller;
import ubicomp.soberdiary3.test.ui.TestQuestionDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TiggerActivity extends Activity {
	
	private TextView chooseResult;
	private Button confirm_button, cancel_button;
	private ImageView note_smile, note_notgood, note_cry, note_try, note_urge;
	private Spinner items;
	private ArrayAdapter adapter_smile, adapter_notgood, adapter_cry, adapter_try, adapter_urge;
	
	
	private Activity activity;
	
	private TestQuestionDialog msgBox;
	
	private AlertDialog.Builder dialog;
	
	
	
	private ViewPager viewPager;
	//private StatisticPagerAdapter typeViewAdapter;
	//private TriggerTypeAdapter typeViewAdapter;
	private LayoutInflater inflater;
	private View view1, view2;
	private List<View> views;
	private boolean done, doneByDoubleClick;
	private TestQuestionCaller testQuestionCaller;
	private QuestionFile questionFile;
	
	//private String[] choose;
	//private Resources res;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);
		
		activity = this;
		
		confirm_button =(Button)findViewById(R.id.note_confirm);
		cancel_button =(Button)findViewById(R.id.note_cancel);
		
		note_smile =(ImageView)findViewById(R.id.note_smile);
		note_notgood =(ImageView)findViewById(R.id.note_notgood);
		note_cry =(ImageView)findViewById(R.id.note_cry);
		note_try =(ImageView)findViewById(R.id.note_try);
		note_urge=(ImageView)findViewById(R.id.note_urge);
		//note_smile.setOnClickListener(new smileOnClickListener());
		//note_notgood.setOnClickListener(new notgoodOnClickListener());
		//note_cry.setOnClickListener(new cryOnClickListener());
		//note_try.setOnClickListener(new tryOnClickListener());
		//note_urge.setOnClickListener(new urgeOnClickListener());
		
		note_smile.setOnClickListener(new MyOnClickListener());
		note_notgood.setOnClickListener(new MyOnClickListener());
		note_cry.setOnClickListener(new MyOnClickListener());
		note_try.setOnClickListener(new MyOnClickListener());
		note_urge.setOnClickListener(new MyOnClickListener());
		
		note_smile.setOnLongClickListener(new MyOnLongClickListener());
		note_notgood.setOnLongClickListener(new MyOnLongClickListener());
		note_cry.setOnLongClickListener(new MyOnLongClickListener());
		note_try.setOnLongClickListener(new MyOnLongClickListener());
		note_urge.setOnLongClickListener(new MyOnLongClickListener());
	
		items = (Spinner)findViewById(R.id.note_items);
		
		viewPager = (ViewPager)findViewById(R.id.type_page);
		
		
		
		confirm_button.setOnClickListener(new EndOnClickListener());
		cancel_button.setOnClickListener(new CancelOnClickListener());
		//

		/*dialog = new AlertDialog.Builder(this);
        dialog.setTitle("應對建議");
        dialog.setMessage("請試著想像自己躺在海上漂浮著");
        //dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setCancelable(false);  
        dialog.setPositiveButton("已完成", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
                // 按下PositiveButton要做的事  
             //Toast.makeText(TiggerActivity.this, "Great Job!", Toast.LENGTH_SHORT).show();
            	CustomToast.generateToast(R.string.after_questionnaire, 2);
             setResult(RESULT_OK);
             finish();
            }  
        }); 
        dialog.setNegativeButton("下次做", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int which) {
        		// TODO Auto-generated method stub
        		Toast.makeText(TiggerActivity.this, "要記得做喔!", Toast.LENGTH_SHORT).show();
        		setResult(RESULT_OK);
                finish();
        	}
        });*/
		
		dialog = new AlertDialog.Builder(this);
        dialog.setTitle("說明");
        dialog.setMessage("這是說明測試");
        //dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setCancelable(true);  
        
        
        
        enableSend(false);
        
        adapter_smile = ArrayAdapter.createFromResource(this, R.array.note_positive, android.R.layout.simple_spinner_item);
        adapter_smile.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        adapter_notgood = ArrayAdapter.createFromResource(this, R.array.note_notgood, android.R.layout.simple_spinner_item);
        adapter_notgood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        adapter_cry = ArrayAdapter.createFromResource(this, R.array.note_negative, android.R.layout.simple_spinner_item);
        adapter_cry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        adapter_try = ArrayAdapter.createFromResource(this, R.array.note_selftest, android.R.layout.simple_spinner_item);
        adapter_try.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        adapter_urge = ArrayAdapter.createFromResource(this, R.array.note_temptation, android.R.layout.simple_spinner_item);
        adapter_urge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, R.array.note_conflict);
        //adapter.setNotifyOnChange(true);
        
        //res = getResources();
        //choose = res.getStringArray(R.array.note_positive);
		//msgBox = new TestQuestionDialog(this, this, main_layout);
		/*
		chooseResult = (TextView) findViewById(R.id.trigger_result);
		
		ArrayAdapter<CharSequence> arrAdapWeekday=
				ArrayAdapter.createFromResource( this, R.array.trigger_list, android.R.layout.simple_list_item_1);
		
		setListAdapter(arrAdapWeekday);
		
		ListView listview = getListView();*/
		//listview.setOnItemClickListener(listViewOnItemClick);
		
		
	}
	
	/*private void setStorage() {
		File dir = MainStorage.getMainStorageDirectory();

		mainDirectory = new File(dir, String.valueOf(timestamp));
		if (!mainDirectory.exists())
			if (!mainDirectory.mkdirs()) {
				return;
			}

		bracFileHandler = new BracValueFileHandler(mainDirectory,
				String.valueOf(timestamp));
		bracDebugHandler = new BracValueDebugHandler(mainDirectory,
				String.valueOf(timestamp));
		imgFileHandler = new ImageFileHandler(mainDirectory,
				String.valueOf(timestamp));
		questionFile = new QuestionFile(mainDirectory);
	}*/
		
	private class SpinnerXMLSelectedListener implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {  
			//Toast.makeText(this, "你選的是"+choose[arg2], Toast.LENGTH_SHORT).show();
            //view2.setText("你使用什么样的手机："+adapter2.getItem(arg2));  
        }  
  
        public void onNothingSelected(AdapterView<?> arg0) {  
              
        }  
	}
	class MyOnLongClickListener implements OnLongClickListener{
	    public boolean onLongClick(View v){
	    	dialog.show();
	    	return true;
	    }
	}
	
	
	
	class MyOnClickListener implements OnClickListener{
	    public void onClick(View v){
	    	
	    	enableSend(true, true);
	    	
	        switch(v.getId()){
	        case R.id.note_smile:
				note_smile.setBackgroundColor(Color.WHITE);
				note_notgood.setBackgroundColor(Color.DKGRAY);
				note_cry.setBackgroundColor(Color.DKGRAY);
				note_try.setBackgroundColor(Color.DKGRAY);
				note_urge.setBackgroundColor(Color.DKGRAY);
				
				items.setAdapter(adapter_smile);
				items.setPrompt("正面情緒");
		        items.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
		        items.setVisibility(View.VISIBLE);  
		        items.performClick();
	            break;
	        case R.id.note_notgood:
	        	note_smile.setBackgroundColor(Color.DKGRAY);
				note_notgood.setBackgroundColor(Color.WHITE);
				note_cry.setBackgroundColor(Color.DKGRAY);
				note_try.setBackgroundColor(Color.DKGRAY);
				note_urge.setBackgroundColor(Color.DKGRAY);
				
				items.setAdapter(adapter_notgood);
				items.setPrompt("身體不舒服");
		        items.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
		        items.setVisibility(View.VISIBLE);  
		        items.performClick();
	            break;
	        case R.id.note_cry:
	        	note_smile.setBackgroundColor(Color.DKGRAY);
				note_notgood.setBackgroundColor(Color.DKGRAY);
				note_cry.setBackgroundColor(Color.WHITE);
				note_try.setBackgroundColor(Color.DKGRAY);
				note_urge.setBackgroundColor(Color.DKGRAY);
				
				items.setAdapter(adapter_cry);
				items.setPrompt("負面情緒");
		        items.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
		        items.setVisibility(View.VISIBLE);  
		        items.performClick();
	            break;
	        case R.id.note_try:
	        	note_smile.setBackgroundColor(Color.DKGRAY);
				note_notgood.setBackgroundColor(Color.DKGRAY);
				note_cry.setBackgroundColor(Color.DKGRAY);
				note_try.setBackgroundColor(Color.WHITE);
				note_urge.setBackgroundColor(Color.DKGRAY);
				
				items.setAdapter(adapter_try);
				items.setPrompt("自我測驗");
		        items.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
		        items.setVisibility(View.VISIBLE);  
		        items.performClick();
	            break;
	        case R.id.note_urge:
	        	note_smile.setBackgroundColor(Color.DKGRAY);
				note_notgood.setBackgroundColor(Color.DKGRAY);
				note_cry.setBackgroundColor(Color.DKGRAY);
				note_try.setBackgroundColor(Color.DKGRAY);
				note_urge.setBackgroundColor(Color.WHITE);
				
				items.setAdapter(adapter_urge);
				items.setPrompt("誘惑和衝動");
		        items.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
		        items.setVisibility(View.VISIBLE);  
		        items.performClick();
	            break;
	        }
	    }
	}

	
	private void enableSend(boolean enable) {
		if (enable) {
			//send.setTextColor(highlight_color);
			//notSend.setTextColor(highlight_color);
		} else {
			//send.setTextColor(text_color);
			//notSend.setTextColor(text_color);
		}
		done = enable;
		doneByDoubleClick = false;
	}

	private void enableSend(boolean enable, boolean click) {
		if (enable) {
			//send.setTextColor(highlight_color);
			//notSend.setTextColor(highlight_color);
		} else {
			//send.setTextColor(text_color);
			//notSend.setTextColor(text_color);
		}
		done = enable;
		doneByDoubleClick = click;
	}


	private class EndOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			if (!done) {
				CustomToastSmall.generateToast(R.string.msg_box_toast_send);
				enableSend(true, true);
				return;
			}
			//testQuestionCaller.writeQuestionFile(1, 1);
			//testQuestionCaller.writeQuestionFile(emotion, craving);
			setResult(RESULT_OK);
            finish();
		}
	}

	private class CancelOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {

			if (!done) {
				CustomToastSmall.generateToast(R.string.msg_box_toast_cancel);
				enableSend(true);
				return;
			}
			//boxLayout.setVisibility(View.INVISIBLE);
			//testQuestionCaller.writeQuestionFile(emotion, craving);
			setResult(RESULT_OK);
            finish();
		}
	}
	
	
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
	/*
	private AdapterView.OnItemClickListener listViewOnItemClick = new AdapterView.OnItemClickListener() {
		
		@Override
		public void OnItemClick (AdapterView<?> parent, View view, int position, long id ){
			
			chooseResult.setText(((TextView) view).getText() );
		}
	};*/
	
 
	
}
