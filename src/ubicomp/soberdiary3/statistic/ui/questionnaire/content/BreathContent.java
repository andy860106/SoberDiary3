package ubicomp.soberdiary3.statistic.ui.questionnaire.content;

import ubicomp.soberdiary3.R;
import ubicomp.soberdiary3.statistic.ui.QuestionnaireDialog;
import ubicomp.soberdiary3.statistic.ui.questionnaire.listener.EndOnClickListener;

public class BreathContent extends QuestionnaireContent {

	public BreathContent(QuestionnaireDialog msgBox) {
		super(msgBox);
	}

	@Override
	protected void setContent() {
		msgBox.showCloseButton(false);
		msgBox.setNextButton("", null);
		setHelp(R.string.breath_check_help);
		msgBox.showQuestionnaireLayout(false);
		msgBox.setNextButton(R.string.ok, new EndOnClickListener(msgBox));
	}

}
