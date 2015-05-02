package ubicomp.soberdiary3.statistic.ui.questionnaire.content;

import ubicomp.soberdiary3.R;
import ubicomp.soberdiary3.statistic.ui.QuestionnaireDialog;
import ubicomp.soberdiary3.statistic.ui.questionnaire.listener.CallCheckOnClickListener;
import ubicomp.soberdiary3.statistic.ui.questionnaire.listener.CloseClickListener;
import ubicomp.soberdiary3.statistic.ui.questionnaire.listener.EndOnClickListener;
import ubicomp.soberdiary3.statistic.ui.questionnaire.listener.SelectedListener;
import ubicomp.soberdiary3.system.config.PreferenceControl;


public class CopingSkillContent extends QuestionnaireContent {
	
	private int type;
	public static final int TYPE_FAMILY = 2, TYPE_SOCIAL = 3;

	public CopingSkillContent(QuestionnaireDialog msgBox) {
		super(msgBox);
		
		
		//this.type = type;
	}

	@Override
	protected void setContent() {
		setHelp(R.string.suggestion1);
		msgBox.showDialog();
		msgBox.showQuestionnaireLayout(true);
		msgBox.setNextButton(R.string.done, new EndOnClickListener(msgBox));
	}

}
