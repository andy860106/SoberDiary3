package ubicomp.soberdiary3.statistic.ui.questionnaire.content;

import ubicomp.soberdiary3.R;
import ubicomp.soberdiary3.statistic.ui.QuestionnaireDialog;
import ubicomp.soberdiary3.statistic.ui.questionnaire.listener.CallDialOnClickListener;
import android.content.Context;

public class CallCheckContent extends QuestionnaireContent {

	private String name, phone;
	private boolean isEmotion = false;

	public CallCheckContent(QuestionnaireDialog msgBox, String name,
			String phone) {
		super(msgBox);
		this.name = name;
		this.phone = phone;
	}

	public CallCheckContent(QuestionnaireDialog msgBox, String name,
			String phone, boolean isEmotion) {
		super(msgBox);
		this.name = name;
		this.phone = phone;
		this.isEmotion = isEmotion;
	}

	@Override
	protected void setContent() {
		msgBox.setNextButton("", null);
		if (isEmotion) {
			setHelp(R.string.call_check_help_emotion_hot_line);
		} else {
			Context context = msgBox.getContext();
			String call_check = context.getString(R.string.call_check_help);
			String question_sign = context.getString(R.string.question_sign);
			setHelp(call_check + " " + name + " " + question_sign);
		}
		msgBox.showQuestionnaireLayout(false);
		msgBox.setNextButton(R.string.ok, new CallDialOnClickListener(msgBox,
				phone));
	}

}
