package com.fuxu.tools;
import com.fuxu.definition.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.*;

@SuppressWarnings("deprecation")
public class RemoteJarLoader {
	private class MD5 {
		private String inStr;
		private MessageDigest md5;

		/**
		 * Constructs the MD5 object and sets the string whose MD5 is to be
		 * computed.
		 * 
		 * @param inStr
		 *            the <code>String</code> whose MD5 is to be computed
		 */
		public MD5(String inStr) {
			this.inStr = inStr;
			try {
				this.md5 = MessageDigest.getInstance("MD5");
			} catch (Exception e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}

		/**
		 * Computes the MD5 fingerprint of a string.
		 * 
		 * @return the MD5 digest of the input <code>String</code>
		 */
		public String compute() {
			char[] charArray = this.inStr.toCharArray();
			byte[] byteArray = new byte[charArray.length];
			for (int i = 0; i < charArray.length; i++)
				byteArray[i] = (byte) charArray[i];
			byte[] md5Bytes = this.md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		}
	}
	
	private URLClassLoader cl;
	
	public RemoteJarLoader(String user, String password, String host, String remotePath) throws MalformedURLException
	{
		StringBuffer strBuffer = new StringBuffer(1024);
		strBuffer.append("ftp://");
		//strBuffer.append(new MD5(user).compute());
		//strBuffer.append(":");
		//strBuffer.append(new MD5(password).compute());
		//strBuffer.append("@");
		strBuffer.append(host);
		if (!remotePath.startsWith("/"))
		{
			strBuffer.append("/");
		}
		strBuffer.append(remotePath);
		URL url = new URL(strBuffer.toString());
		URL[] urls = new URL[] { url };
		this.cl = new URLClassLoader(urls);
	}
	@SuppressWarnings("unchecked")
	public Class loadClass(String className)
	{
		try {
			Class cls = cl.loadClass(className);
			return cls;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	public static void main(String[] args)
	{
		try {
			RemoteJarLoader rjl = new RemoteJarLoader("melphis", "1234", "192.168.0.50", "/formular.jar");
			IExperienceManager em = (IExperienceManager)rjl.loadClass("com.fuxu.formular.ExperienceManager").newInstance();
			System.out.println("Exp for level 10 is :" + em.getLevelExp(10));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
