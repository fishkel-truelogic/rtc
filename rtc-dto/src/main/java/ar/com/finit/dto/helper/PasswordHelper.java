package ar.com.finit.dto.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author leo
 */
public class PasswordHelper {

	static String HexForByte(byte b) {
		String Hex = Integer.toHexString((int) b & 0xff);
		boolean hasTwoDigits = (2 == Hex.length());
		if (hasTwoDigits) {
			return Hex;
		}else {
			return "0" + Hex;
		}
	}

	static String HexForBytes(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			sb.append(HexForByte(b));
		}
		return sb.toString();
	}

	/**
	 * Codifica la contraseña con el metodo de hashing MD5 HEX
	 * @param password
	 * @return
	 */
	public static String hashPassword(String password) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] digest = md5.digest(password.getBytes());
		return HexForBytes(digest);
	}
}
