package com.goldsmiths.comp.sec;

public class ShiftCeasar {

	public String encrypt(String plaintext, int shift) {
		if (shift > 26) {
			shift %= 26;
		}
		String cipher = "";
		int textlenght = plaintext.length();
		for (int i = 0; i < textlenght; i++) {

			char c = plaintext.charAt(i);
			char ch = (char) (c + shift);
			cipher += ch;

		}

		return cipher;
	}

	public String dencrypt(String plaintext, int shift) {
		if (shift > 26) {
			shift %= 26;
		} else {

		}
		String cipher = "";
		int textlenght = plaintext.length();
		for (int i = 0; i < textlenght; i++) {

			char c = plaintext.charAt(i);
			char ch = (char) (c - shift);
			cipher += ch;

		}

		return cipher;
	}
}