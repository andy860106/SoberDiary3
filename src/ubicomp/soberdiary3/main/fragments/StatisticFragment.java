package ubicomp.soberdiary3.main.fragments;

import java.util.ArrayList;

import ubicomp.soberdiary3.R;
import ubicomp.soberdiary3.main.MainActivity;
import ubicomp.soberdiary3.system.clicklog.ClickLog;
import ubicomp.soberdiary3.system.clicklog.ClickLogId;
import ubicomp.soberdiary3.system.config.PreferenceControl;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class StatisticFragment extends Fragment {

	private View view;
	private Activity activity;
	private ViewPager statisticView;

	private RelativeLayout allLayout;
	private ImageView[] dots;
	private Drawable dot_on, dot_off;
	private LinearLayout analysisLayout;

	private ScrollView analysisView;
	private StatisticFragment statisticFragment;

	private ImageView questionButton;

	private AlphaAnimation questionAnimation;



	private int notify_action = 0;

	// private static final String TAG = "STATISTIC";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = getActivity();

		dot_on = getResources().getDrawable(R.drawable.dot_on);
		dot_off = getResources().getDrawable(R.drawable.dot_off);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_2, container, false);

		return view;
	}

	public void onResume() {
		super.onResume();

		ClickLog.Log(ClickLogId.STATISTIC_ENTER);
		enablePage(true);
		statisticFragment = this;


		Bundle data = getArguments();
		if (data != null) {
			int action = data.getInt("action");
			data.putInt("action", 0);
			if (action == MainActivity.ACTION_QUESTIONNAIRE) {
				notify_action = action;
			}
		}

		//loadHandler.sendEmptyMessage(0);
	}

	public void onPause() {

		clear();
		ClickLog.Log(ClickLogId.STATISTIC_LEAVE);
		super.onPause();
	}

	private void clear() {
		removeRadarChart();
	}



	@SuppressLint("HandlerLeak")




	public void setQuestionAnimation() {
		questionButton.setVisibility(View.VISIBLE);
		int result = PreferenceControl.getTestResult();
		if (result == -1) {
			questionAnimation.cancel();
			questionButton.setAnimation(null);
			if (Build.VERSION.SDK_INT >= 11)
				questionButton.setAlpha(1.0F);
		} else {
			questionButton.setAnimation(questionAnimation);
			questionAnimation.start();
		}
	}

	public void enablePage(boolean enable) {
		statisticView.setEnabled(enable);
		analysisView.setEnabled(enable);
		questionButton.setEnabled(enable);
		MainActivity.getMainActivity().enableTabAndClick(enable);
	}



	private View rv;
	private View dv;

	public void showRadarChart(ArrayList<Double> scoreList) {
		removeRadarChart();
		removeDetailChart();

		//rv = radarChart.getView();

		View.OnClickListener[] onClickListeners = new OnClickListener[4];
		for (int i = 0; i < 4; ++i) {
			onClickListeners[i] = new RadarOnClickListener(i);
		}
		//radarChart.setting(scoreList, onClickListeners);
		allLayout.addView(rv);
		RelativeLayout.LayoutParams rvParam = (RelativeLayout.LayoutParams) rv
				.getLayoutParams();
		rvParam.width = rvParam.height = LayoutParams.MATCH_PARENT;
		allLayout.invalidate();
		rv.invalidate();
		rv.setOnClickListener(new RadarOnClickListener(-1));
		ClickLog.Log(ClickLogId.STATISTIC_RADAR_CHART_OPEN);
		enablePage(false);
	}

	private class RadarOnClickListener implements View.OnClickListener {

		private int type;

		public RadarOnClickListener(int type) {
			this.type = type;
		}

		@Override
		public void onClick(View v) {
			ClickLog.Log(ClickLogId.STATISTIC_RADAR_CHART_CLOSE);
			removeRadarChart();
			if (type >= 0) {
				ClickLog.Log(ClickLogId.STATISTIC_DETAIL_CHART_OPEN + type);
				addDetailChart(type);
			}
		}

	}

	public void removeRadarChart() {
		if (rv != null && rv.getParent() != null
				&& rv.getParent().equals(allLayout))
			allLayout.removeView(rv);
		enablePage(true);
	}

	public void addDetailChart(int type) {
		removeRadarChart();
		removeDetailChart();

		//dv = detailChart.getView();
		allLayout.addView(dv);
		RelativeLayout.LayoutParams dvParam = (RelativeLayout.LayoutParams) dv
				.getLayoutParams();
		dvParam.width = dvParam.height = LayoutParams.MATCH_PARENT;
		//detailChart.setting(type, new DatabaseControl().getMyRank());
		dv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ClickLog.Log(ClickLogId.STATISTIC_DETAIL_CHART_CLOSE);
				removeDetailChart();
			}
		});
		dv.invalidate();
		enablePage(false);
	}

	public void removeDetailChart() {
		if (dv != null && dv.getParent() != null
				&& dv.getParent().equals(allLayout))
			allLayout.removeView(dv);
		//detailChart.hide();
		enablePage(true);
	}



}
