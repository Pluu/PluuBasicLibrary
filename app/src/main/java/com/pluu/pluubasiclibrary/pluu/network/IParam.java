package com.pluu.pluubasiclibrary.pluu.network;

/**
 * Network Parameter
 * @author PLUUSYSTEM
 */
public interface IParam {
	// 공통 정보
	// TODO :

	public static final String RES_HEADER = "header";
	public static final String RES_CODE = "resCode";
	public static final String RES_MSG = "resMsg";
	
	public final int CODE_ERROR = 9999;
	
	/** Android Side : Response Code */
	public enum CODE
	{
		/** Request Result Default */
		NONE(-1),
		/** Result System Error */
		SYSTEM_ERROR(CODE_ERROR),
		/** Result 응답성공 */
		SUCCESS(0),
		/** Result 데이터없음 */
		NO_DATA(11);

		private final int code;
		
		CODE(final int code)
		{
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
		
		/**
		 * Get Response Code 
		 * @param code Server Result Code
		 * @return Client Side Code;
		 */
		public static CODE getCodeGenerator(int code)
		{
			for (CODE item : CODE.values()) {
				if (code == item.getCode()) {
					return item;
				}
			}
			return CODE.NONE;
		}
	}
	
}
