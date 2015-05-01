package ubicomp.soberdiary3;

import ubicomp.soberdiary3.test.ui.TestQuestionDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
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
	
	private Activity activity;
	
	private TestQuestionDialog msgBox;
	
	private AlertDialog.Builder dialog;
	
	private ArrayAdapter adapter_smile, adapter_notgood, adapter_cry, adapter_try, adapter_urge;

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
		note_urge=(ImageView)findViewById(R.id.note_urge);;
		
		items = (Spinner)findViewById(R.id.note_items);
		
		
		note_smile.setOnClickListener(new smileOnClickListener());
		note_notgood.setOnClickListener(new notgoodOnClickListener());
		note_cry.setOnClickListener(new cryOnClickListener());
		note_try.setOnClickListener(new tryOnClickListener());
		note_urge.setOnClickListener(new urgeOnClickListener());
		
		confirm_button.setOnClickListener(new confirmOnClickListener());
		cancel_button.setOnClickListener(new confirmOnClickListener());
		
		
		
		
		dialog = new AlertDialog.Builder(this);
        dialog.setTitle("應對建議");
        dialog.setMessage("請試著想像自己躺在海上漂浮著");
        //dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setCancelable(false);  
        dialog.setPositiveButton("已完成", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
                // 按下PositiveButton要做的事  
             Toast.makeText(TiggerActivity.this, "Great Job!", Toast.LENGTH_SHORT).show();
             setResult(RESULT_OK);
             finish();
            }  
        }); 
        dialog.setNegativeButton("下次做", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int which) {
        		// TODO Auto-generated method stub
        		Toast.makeText(TiggerActivity.this, "要記得做喔!", Toast.LENGTH_SHORT).show();
        	}
        });
        
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
        //TODO: 按下按鈕後切回下個頁面
        
        
		//msgBox = new TestQuestionDialog(this, this, main_layout);
		/*
		chooseResult = (TextView) findViewById(R.id.trigger_result);
		
		ArrayAdapter<CharSequence> arrAdapWeekday=
				ArrayAdapter.createFromResource( this, R.array.trigger_list, android.R.layout.simple_list_item_1);
		
		setListAdapter(arrAdapWeekday);
		
		ListView listview = getListView();*/
		//listview.setOnItemClickListener(listViewOnItemClick);
		
		
	}
	private class SpinnerXMLSelectedListener implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) {  
            //view2.setText("你使用什么样的手机："+adapter2.getItem(arg2));  
        }  
  
        public void onNothingSelected(AdapterView<?> arg0) {  
              
        }  
	}
	
	private class smileOnClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			items.setAdapter(adapter_smile);
	        items.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
	        items.setVisibility(View.VISIBLE);  
			
			//ialog.show();
			//final AlertDialog alertDialog = getAlertDialog("這是一個對話框","請選擇......");
		}
	}
	
	private class notgoodOnClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			items.setAdapter(adapter_notgood);
	        items.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
	        items.setVisibility(View.VISIBLE);  
			
			//ialog.show();
			//final AlertDialog alertDialog = getAlertDialog("這是一個對話框","請選擇......");
		}
	}
	
	private class cryOnClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			items.setAdapter(adapter_cry);
	        items.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
	        items.setVisibility(View.VISIBLE);  
			
			//ialog.show();
			//final AlertDialog alertDialog = getAlertDialog("這是一個對話框","請選擇......");
		}
	}
	
	private class tryOnClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			items.setAdapter(adapter_try);
	        items.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
	        items.setVisibility(View.VISIBLE);  
			
			//ialog.show();
			//final AlertDialog alertDialog = getAlertDialog("這是一個對話框","請選擇......");
		}
	}
	
	private class urgeOnClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			items.setAdapter(adapter_urge);
	        items.setOnItemSelectedListener(new SpinnerXMLSelectedListener());
	        items.setVisibility(View.VISIBLE);  
			
			//ialog.show();
			//final AlertDialog alertDialog = getAlertDialog("這是一個對話框","請選擇......");
		}
	}
	
	
	
	private class confirmOnClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			dialog.show();
			//final AlertDialog alertDialog = getAlertDialog("這是一個對話框","請選擇......");
		}
	}
	
	private class cancelOnClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			
		}
	}
	
    /*private AlertDialog getAlertDialog(String title,String message){
        //產生一個Builder物件
        Builder builder = new AlertDialog.Builder(TiggerActivity.this);
        //設定Dialog的標題
        builder.setTitle(title);
        //設定Dialog的內容
        builder.setMessage(message);
        //設定Positive按鈕資料
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //按下按鈕時顯示快顯
                Toast.makeText(TiggerActivity.this, "您按下OK按鈕", Toast.LENGTH_SHORT).show();
            }
        });
        //設定Negative按鈕資料
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //按下按鈕時顯示快顯
                Toast.makeText(TiggerActivity.this, "您按下Cancel按鈕", Toast.LENGTH_SHORT).show();
            }
        });
        //利用Builder物件建立AlertDialog
        return builder.create();
    }*/
	
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
