package ubicomp.soberdiary3.statistic.ui.questionnaire.listener;

import ubicomp.soberdiary3.statistic.ui.QuestionnaireDialog;
import ubicomp.soberdiary3.statistic.ui.questionnaire.content.InspireContent;
import ubicomp.soberdiary3.system.clicklog.ClickLog;
import ubicomp.soberdiary3.system.clicklog.ClickLogId;
import android.view.View;

public class ReadingOnClickListener extends QuestionnaireOnClickListener {

	public ReadingOnClickListener(QuestionnaireDialog msgBox) {
		super(msgBox);
	}

	@Override
	public void onClick(View arg0) {
		ClickLog.Log(ClickLogId.STATISTIC_QUESTION_READING);
		seq.add(3);
		contentSeq.add(new InspireContent(msgBox));
		contentSeq.get(contentSeq.size() - 1).onPush();
	}

}
