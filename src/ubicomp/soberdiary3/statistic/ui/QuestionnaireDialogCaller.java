package ubicomp.soberdiary3.statistic.ui;

import ubicomp.soberdiary3.main.ui.EnablePage;
import android.content.Context;

public interface QuestionnaireDialogCaller extends EnablePage {
	public void setQuestionAnimation();

	public void updateSelfHelpCounter();

	public Context getContext();
}
