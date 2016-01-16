package com.lhy.commons.zip;

import java.io.File;
import java.text.MessageFormat;

import org.apache.commons.io.FilenameUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ZipUtils {
	public static String unZip(File file,String destPath,String fileNameCharset,String password){
		if (!file.exists()){
			throw new RuntimeException(MessageFormat.format("File {0} is not exist", file.getName()));
		}
		if (!file.isFile()){
			throw new RuntimeException(MessageFormat.format("File {0} is not a File", file.getName()));
		}		
		try {
			ZipFile zipFile = new ZipFile(file);
			zipFile.setFileNameCharset(fileNameCharset);		
			if (zipFile.isValidZipFile()){
				if (zipFile.isEncrypted()){
					zipFile.setPassword(password);
				}
				File destPathFile=new File(destPath);
				if (destPathFile.exists()){
					destPathFile.mkdir();
				}
				zipFile.extractAll(destPath);
				return destPath+File.separator+FilenameUtils.getBaseName(file.getName());		
			}else{
				throw new RuntimeException("is not valid zip file");
			}
		} catch (ZipException e) {
			throw new RuntimeException(e);
		}		
	}
}
