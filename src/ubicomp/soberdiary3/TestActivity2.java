package ubicomp.soberdiary3;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import ubicomp.soberdiary3.R;


public class TestActivity2 extends Activity {

	private ViewPager mPager;
	private List<View> listViews; 
	private ImageView cursor;
	private TextView t1, t2, t3;
	private int offset = 0;//移動條圖片的偏移量
	private int currIndex = 0;//當前頁面的編號
	private int bmpWidth;// 移動條圖片的長度
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note2);
		//setContentView(R.layout.test);
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
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
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
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

	}
}