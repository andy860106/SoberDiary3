package ubicomp.soberdiary3.test.bluetooth;

import ubicomp.soberdiary3.test.Tester;

public interface BluetoothCaller extends Tester {
	public void stopDueToInit();

	public void failBT();

	public void setPairMessage();
}
