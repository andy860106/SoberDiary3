package ubicomp.soberdiary3;

import ubicomp.soberdiary3.main.MainActivity;
import ubicomp.soberdiary3.test.ui.TestQuestionDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TiggerActivity extends Activity {
	
	private TextView chooseResult;
	private Button confirm_button, cancel_button;
	
	private TestQuestionDialog msgBox;
	
	private AlertDialog.Builder dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);
		
		confirm_button =(Button)findViewById(R.id.note_confirm);
		cancel_button =(Button)findViewById(R.id.note_cancel);
		
		
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
            }  
        }); 
        dialog.setNegativeButton("下次做", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int which) {
        		// TODO Auto-generated method stub
        		Toast.makeText(TiggerActivity.this, "要記得做喔!", Toast.LENGTH_SHORT).show();
        	}
        });
        
        
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
