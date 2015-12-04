package com.team4.until;

public class ScreenSizeUntil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int screenWidth = ((int) java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize().width);
		int screenHeight = ((int) java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize().height);
		System.out.println(screenWidth + "\n" + screenHeight);
	}

}
