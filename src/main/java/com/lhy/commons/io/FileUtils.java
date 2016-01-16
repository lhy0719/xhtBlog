package com.lhy.commons.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import org.springframework.util.ResourceUtils;

public class FileUtils {
	public static File getClassPathFile(String classPath) throws FileNotFoundException{
		return ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+classPath);		
	}
	public static Iterator<File> getDirFileIterator(File destPath,boolean showChildDir,String... extentions){
		return org.apache.commons.io.FileUtils.iterateFiles(destPath, extentions, showChildDir);
	}
}
