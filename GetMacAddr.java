

import java.net.*;

public class GetMacAddr {
	static byte[] getHardWareAddr() {
		try {
			InetAddress add = InetAddress.getLocalHost();
			NetworkInterface ni = NetworkInterface.getByInetAddress(add);
			byte[] mac = ni.getHardwareAddress();
			return mac;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		byte[] mac = getHardWareAddr();
		if (null != mac) {
			System.out.println("Length: " + mac.length);
			for (byte b : mac) {
				System.out.printf("%x-", b);
			}
			System.out.print("\n");
		}
	}
}
