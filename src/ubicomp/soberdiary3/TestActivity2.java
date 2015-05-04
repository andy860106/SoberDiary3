package ubicomp.soberdiary3;

import java.util.ArrayList;
import java.util.List;

import ubicomp.soberdiary3.main.ui.BarButtonGenerator;
import ubicomp.soberdiary3.main.ui.toast.CustomToastSmall;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class TestActivity2 extends Activity {
	
	private Activity activity;

	//View
	private TextView chooseResult;
	private Button confirm_button, cancel_button;
	private ImageView note_smile, note_notgood, note_cry, note_try, note_urge;
	private Spinner items;
	private ArrayAdapter adapter_smile, adapter_notgood, adapter_cry, adapter_try, adapter_urge;
	private ViewPager mPager;
	private List<View> listViews; 
	private ImageView cursor;
	private TextView t1, t2, t3;
	private AlertDialog.Builder dialog;
	private LinearLayout titleLayout;
	
	
	//Parameter
	private int offset = 0;//移動條圖片的偏移量
	private int currIndex = 0;//當前頁面的編號
	private int bmpWidth;// 移動條圖片的長度
	private boolean done, doneByDoubleClick;
	private Resources resource;
	private ColorStateList csl, csl2;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note2);
		//setContentView(R.layout.test);
		activity = this;
		initCursorPos();
		initView();		
	}
	
	//完成View組件的初始化,並將三個view動態地添加到Adapter對象中
	//然後調用setAdapter將view集合與ViewPager綁定,設置進去的頁面
	//指向第一個頁面setCurrentItem(0),最後設置onclick和onPageChange監聽器
	public void initView()
	{
		//mPager = (ViewPager) findViewById(R.id.vPager);
		mPager = (ViewPager) findViewById(R.id.type_page);
		t1 = (TextView) findViewById(R.id.text1);
		t2 = (TextView) findViewById(R.id.text2);
		t3 = (TextView) findViewById(R.id.text3);
		
		confirm_button =(Button)findViewById(R.id.note_confirm);
		cancel_button =(Button)findViewById(R.id.note_cancel);
		titleLayout = (LinearLayout)findViewById(R.id.note_title_layout);
	
		items = (Spinner)findViewById(R.id.note_items);
		
		confirm_button.setOnClickListener(new EndOnClickListener());
		cancel_button.setOnClickListener(new CancelOnClickListener());
		
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		listViews.add(mInflater.inflate(R.layout.trigger_typeself, null));
		listViews.add(mInflater.inflate(R.layout.trigger_typeother, null));
		listViews.add(mInflater.inflate(R.layout.statistic_day_view, null));
		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(0);
		
		
		t1.setOnClickListener(new MyClickListener(0));
		t2.setOnClickListener(new MyClickListener(1));
		t3.setOnClickListener(new MyClickListener(2));
		
		resource = (Resources) getBaseContext().getResources();  
		csl = (ColorStateList) resource.getColorStateList(R.color.orange);
		csl2 = (ColorStateList) resource.getColorStateList(R.color.toast_point);
		t1.setTextColor(csl );
		t2.setTextColor(csl2);
		t3.setTextColor(csl2);
		
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		enableSend(false);
		
		dialog = new AlertDialog.Builder(this);
        dialog.setTitle("說明");
        dialog.setMessage("這是說明測試");
        //dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setCancelable(true);  
        
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
        
        View title = BarButtonGenerator.createTitleView(R.string.title_activity_trigger);
		titleLayout.addView(title);
        

	}
	
	private class SpinnerXMLSelectedListener implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {  
			Toast.makeText(activity, "你選的是"+items.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
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
	
	
	//初始化指示器的位置,就是下面那個移動條一開始放的地方
    public void initCursorPos() { 
		cursor = (ImageView) findViewById(R.id.cursor);
		bmpWidth = BitmapFactory.decodeResource(getResources(), R.drawable.analysis_title_bar)
				.getWidth();// 獲取圖片寬度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 獲取分辨率寬度
		offset = (screenW / 3 - bmpWidth) / 2;// 計算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 設置動畫初始位置
    }  
	
	//設置點擊事件,點擊上面文字切換頁面的
	public class MyClickListener implements OnClickListener
	{
		private int index = 0;
		public MyClickListener(int i)
		{
			index = i;
		}
		
		@Override
		public void onClick(View arg0) {
			mPager.setCurrentItem(index);
			switch(index){
			case 0:
				t1.setTextColor(csl );
				t2.setTextColor(csl2);
				t3.setTextColor(csl2);
				break;
			case 1:
				t1.setTextColor(csl2 );
				t2.setTextColor(csl);
				t3.setTextColor(csl2);
				break;
			case 2:
				t1.setTextColor(csl2 );
				t2.setTextColor(csl2);
				t3.setTextColor(csl);
				break;
			}
		}
		
	}
	
	
	//監聽頁面切換時間,主要做的是動畫處理,就是移動條的移動
	public class MyOnPageChangeListener implements OnPageChangeListener {
		int one = offset * 2 + bmpWidth;// 移動一頁的偏移量,比如1->2,或者2->3
		int two = one * 2;// 移動兩頁的偏移量,比如1直接跳3

		@Override
		public void onPageSelected(int index) {
			Animation animation = null;
			switch (index) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}
				break;
			}
			currIndex = index;
			animation.setFillAfter(true);// true表示圖片停在動畫結束位置
			animation.setDuration(300); //設置動畫時間為300毫秒
			cursor.startAnimation(animation);//開始動畫
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {}
	
	}
	public class MyPagerAdapter extends PagerAdapter {

		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View v, int pos) {
			
			switch (pos){
				case 0:
					note_smile =(ImageView)mListViews.get(0).findViewById(R.id.note_smile);
					note_notgood =(ImageView)mListViews.get(0).findViewById(R.id.note_notgood);
					note_cry =(ImageView)mListViews.get(0).findViewById(R.id.note_cry);
					note_try =(ImageView)mListViews.get(0).findViewById(R.id.note_try);
					note_urge=(ImageView)mListViews.get(0).findViewById(R.id.note_urge);
					
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
					
			       
			    break;
	        }
			
			
			((ViewPager) v).addView(mListViews.get(pos), 0);
			return mListViews.get(pos);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

	}
}