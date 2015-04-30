package ubicomp.soberdiary3.system.check;

import ubicomp.soberdiary3.main.App;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkCheck {

	public static boolean networkCheck() {
		ConnectivityManager connectivityManager = (ConnectivityManager) App
				.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
		if (activeNetwork == null)
			return false;
		return activeNetwork.isConnected();
	}

}
