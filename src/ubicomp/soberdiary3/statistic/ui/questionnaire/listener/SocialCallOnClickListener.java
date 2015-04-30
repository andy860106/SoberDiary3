package ubicomp.soberdiary3.statistic.ui.questionnaire.listener;

import ubicomp.soberdiary3.statistic.ui.QuestionnaireDialog;
import ubicomp.soberdiary3.statistic.ui.questionnaire.content.ConnectContent;
import ubicomp.soberdiary3.system.clicklog.ClickLog;
import ubicomp.soberdiary3.system.clicklog.ClickLogId;
import android.view.View;

public class SocialCallOnClickListener extends QuestionnaireOnClickListener {

	public SocialCallOnClickListener(QuestionnaireDialog msgBox) {
		super(msgBox);
	}

	@Override
	public void onClick(View v) {
		ClickLog.Log(ClickLogId.STATISTIC_QUESTION_SOCIAL);
		contentSeq.add(new ConnectContent(msgBox, ConnectContent.TYPE_SOCIAL));
		contentSeq.get(contentSeq.size() - 1).onPush();
	}

}
