package com.pluu.pluubasiclibrary.pluu.network_Kakao.util;


import com.pluu.pluubasiclibrary.BuildConfig;

/**
 * Helper Class
 * Created by nohhs on 2015-10-12.
 */
public class Helper {

	/**
	 * Build Deploy Phase
	 */
	public enum DeployPhase {
		Debug,
		Release;

		public static DeployPhase convert(String i) {
			for (DeployPhase current : values()) {
				if (current.toString().toLowerCase().equals(i)) {
					return current;
				}
			}
			return Release;
		}

		public static DeployPhase current() {
			return convert(BuildConfig.DEPLOY_PHASE);
		}
	}

}
