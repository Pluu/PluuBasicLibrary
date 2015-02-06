package com.pluu.pluubasiclibrary.pluu.common.error;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * 파일 생성 및 로그 자료 생성 Class
 * Created by PLUUSYSTEM on 2014-11-27.
 */
public class LogWriter {
	private String savePath;
	private String fileName;
	private boolean timestamp;

	public LogWriter(boolean timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return savePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void openFile(String rootPath, String fileName, String fileExt) {
		StringBuffer path = new StringBuffer();
		path.append(rootPath).append(File.separator);
		path.append(fileName);

		String curTime = "";
		if (timestamp) {
			curTime = "_" + currTime("yyyy_MM_dd-HH_mm_ss");
			path.append(curTime);
		}
		path.append("." + fileExt);

		this.savePath = path.toString();
		this.fileName = fileName + curTime + "." + fileExt;
	}

	public void clear() {
		File file = new File(savePath);
		if (file.exists()) {
			file.delete();
		}
	}

	public void write(String msg){
		try{
			StringBuffer sb = new StringBuffer();
			if (timestamp) {
				sb.append("[" + currTime("yyyy/MM/dd HH:mm:ss") + "] ");
			}
			sb.append(msg);
			sb.append("\r\n");

			File file = new File(savePath);
			if (file.getParentFile().exists() == false) {
				// 로그 작업 폴더 생성
				file.getParentFile().mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(file, true);
			fos.write(sb.toString().getBytes());

			fos.flush();
			fos.close();
			fos = null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static String currTime(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		long tempTime = System.currentTimeMillis();
		return sdf.format(tempTime);
	}
}
