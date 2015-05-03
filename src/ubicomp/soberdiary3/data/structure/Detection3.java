package ubicomp.soberdiary3.data.structure;

public class Detection3 {

	private float brac;
	private TimeValue tv;
	private int trigger_type;
	private int trigger_item;
	private boolean isPrime;
	private int weeklyScore;
	private int score;
	public static final float BRAC_THRESHOLD = 0.06f;
	public static final float BRAC_THRESHOLD_HIGH = 0.25f;
	private final static int MAX_WEEKLY_SCORE = 42;

	public Detection3(float brac, long timestamp, int trigger_type, int trigger_item,
			boolean isPrime, int weeklyScore, int score) {
		this.brac = brac;
		this.tv = TimeValue.generate(timestamp);
		this.trigger_type = trigger_type;
		this.trigger_item = trigger_item;
		this.isPrime = isPrime;
		this.weeklyScore = weeklyScore;
		this.score = score;
	}

	public Detection3(float brac, long timestamp, int trigger_type, int trigger_item,
			boolean isPrime, int weeklyScore, int score, boolean ver1) {
		this.brac = brac;
		this.tv = TimeValueVer1.generate(timestamp);
		this.trigger_type = trigger_type;
		this.trigger_item = trigger_item;
		this.isPrime = isPrime;
		this.weeklyScore = weeklyScore;
		this.score = score;
	}

	public boolean isSameTimeBlock(Detection3 d) {
		return d != null && tv != null && tv.isSameTimeBlock(d.tv);
	}

	public boolean isPass() {
		return brac < BRAC_THRESHOLD;
	}

	public static float weeklyScoreToProgress(int score) {
		float progress = (float) score * 100F / MAX_WEEKLY_SCORE;
		if (progress > 100.f)
			return 100.f;
		return progress;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(tv.toString());
		sb.append(' ');
		sb.append(trigger_type);
		sb.append(' ');
		sb.append(trigger_item);
		sb.append(' ');
		sb.append(isPrime);
		sb.append(' ');
		sb.append(weeklyScore);
		sb.append(' ');
		sb.append(score);
		return sb.toString();
	}

	public float getBrac() {
		return brac;
	}

	public TimeValue getTv() {
		return tv;
	}

	public int getEmotion() {
		return trigger_type;
	}

	public int getCraving() {
		return trigger_item;
	}

	public boolean isPrime() {
		return isPrime;
	}

	public int getWeeklyScore() {
		return weeklyScore;
	}

	public int getScore() {
		return score;
	}

}
