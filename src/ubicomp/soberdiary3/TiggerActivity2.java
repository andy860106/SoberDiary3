package ubicomp.soberdiary3;

import java.util.List;

import ubicomp.soberdiary3.main.ui.toast.CustomToast;
import ubicomp.soberdiary3.test.ui.TestQuestionDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TiggerActivity2 extends Activity {
	
	private TextView chooseResult;
	private Button confirm_button, cancel_button;
	private ImageView note_smile, note_notgood, note_cry, note_try, note_urge;
	private Spinner items;
	
	
	private Activity activity;
	
	private TestQuestionDialog msgBox;
	
	private AlertDialog.Builder dialog;
	
	private ArrayAdapter adapter_smile, adapter_notgood, adapter_cry, adapter_try, adapter_urge;
	
	private ViewPager viewPager;
	//private StatisticPagerAdapter typeViewAdapter;
	//private TriggerTypeAdapter typeViewAdapter;
	private LayoutInflater inflater;
	private MyViewPagerAdapter typeViewAdapter;
	private View view1, view2;
	private List<View> views;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note2);
		
		activity = this;
		
		confirm_button =(Button)findViewById(R.id.note_confirm);
		cancel_button =(Button)findViewById(R.id.note_cancel);
		
		
		

	
		items = (Spinner)findViewById(R.id.note_items);
		
		viewPager = (ViewPager)findViewById(R.id.type_page);
		
		
		
		confirm_button.setOnClickListener(new confirmOnClickListener());
		cancel_button.setOnClickListener(new confirmOnClickListener());
		//
		
		/*
		inflater = LayoutInflater.from(activity);
		view1 = inflater.inflate(R.layout.trigger_typeself, null);
		view2 = inflater.inflate(R.layout.trigger_typeother, null);
		
		views.add(view1);
		views.add(view2);
		viewPager.setAdapter(new MyViewPagerAdapter(views));
		viewPager.setCurrentItem(0);*/
		//viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		
		/*
		typeViewAdapter = new MyViewPagerAdapter();
		typeView.setAdapter(typeViewAdapter);
		//typeView.setOnPageChangeListener(new StatisticOnPageChangeListener());
		typeView.setSelected(true);

		typeViewAdapter.load();
		typeView.setCurrentItem(0);*/
		//
		
		
        
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
        
        
		//msgBox = new TestQuestionDialog(this, this, main_layout);
		/*
		chooseResult = (TextView) findViewById(R.id.trigger_result);
		
		ArrayAdapter<CharSequence> arrAdapWeekday=
				ArrayAdapter.createFromResource( this, R.array.trigger_list, android.R.layout.simple_list_item_1);
		
		setListAdapter(arrAdapWeekday);
		
		ListView listview = getListView();*/
		//listview.setOnItemClickListener(listViewOnItemClick);
		
		
	}
	public class MyViewPagerAdapter extends PagerAdapter{
		private List<View> mListViews;
		
		public MyViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) 	{	
			container.removeView(mListViews.get(position));
		}


		@Override
		public Object instantiateItem(ViewGroup container, int position) {			
			 container.addView(mListViews.get(position), 0);
			 return mListViews.get(position);
		}

		@Override
		public int getCount() {			
			return  mListViews.size();
		}
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {			
			return arg0==arg1;
		}
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
	
	
	
	private class confirmOnClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			
			setResult(RESULT_OK);
            finish();
			//dialog.show();
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
