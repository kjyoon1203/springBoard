package kr.or.ddit.Util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import kr.or.ddit.Util.Model.FileInfo;


public class FileUtil {

	public static FileInfo getFileInfo(String originalFilename) {
		
		String path = getPath();
		
		String uuidFileName = UUID.randomUUID().toString();
		String extName = getExtension(originalFilename);
		File file = new File(path + "\\" + uuidFileName + extName);
		
		FileInfo fileInfo = new FileInfo(file, extName, originalFilename);
		
		return fileInfo;
	}

	private static String getPath() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String yyyyMM = sdf.format(new Date());
		String yyyy = yyyyMM.substring(0, 4);
		String mm = yyyyMM.substring(4);
		
		String path = "e:\\springUpload\\"+yyyy+"\\"+mm;
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdirs();
		}
		return path;
	}

	public static String getExtension(String originalFilename) {
		// 확장자가 없을 경우 공백 리턴
		// 파일명에 .이 여러개일 경우 가장 마지막에 있는 .이 확장자
		String extName = "";
		if(originalFilename.lastIndexOf(".")>0) {
			extName = originalFilename.substring(originalFilename.lastIndexOf("."));
		}
		
		return extName;
	}
	
	
}
