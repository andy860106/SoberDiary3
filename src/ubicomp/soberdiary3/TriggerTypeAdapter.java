package ubicomp.soberdiary3;


import java.util.ArrayList;

import ubicomp.soberdiary3.main.App;
import ubicomp.soberdiary3.statistic.ui.block.StatisticPageView;
import ubicomp.soberdiary3.statistic.ui.block.TriggerTypeView;
import ubicomp.soberdiary3.statistic.ui.block.TriggerTypeView2;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class TriggerTypeAdapter extends PagerAdapter {

	private ArrayList<View> viewsList;
	private View[] views;
	private View view1, view2;
	private LayoutInflater inflater;
	private Context context;
	
	private StatisticPageView[] statisticViews;
	
	public TriggerTypeAdapter() {
		
		viewsList = new ArrayList<View>();	
		statisticViews = new StatisticPageView[2];					
		statisticViews[0] = new TriggerTypeView();
		statisticViews[1] = new TriggerTypeView2();
		
		/*
		this.context = App.getContext();
		inflater = LayoutInflater.from(context);
		view1 = inflater.inflate(R.layout.trigger_typeself, null);
		view2 = inflater.inflate(R.layout.trigger_typeother, null);
		*/
		viewsList.add(statisticViews[0].getView());
		viewsList.add(statisticViews[1].getView());
	}
	@Override
	public int getCount() {
		return viewsList.size();
	}

	@Override
	public Object instantiateItem(View collection, int position) {

		((ViewPager) collection).addView(viewsList.get(position), 0);
		
		return viewsList.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (View) arg1;
	}

	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView(viewsList.get(position));
	}

	public void load() {
		for (int i = 0; i < statisticViews.length; ++i)
			statisticViews[i].load();
	}

	public void onCancel() {
		for (int i = 0; i < statisticViews.length; ++i)
			statisticViews[i].onCancel();
	}

	public void clear() {
		for (int i = 0; i < statisticViews.length; ++i)
			statisticViews[i].clear();
	}

}

