package com.lhy.commons.directorymonitor;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.impl.*;
import java.io.File;

/*
 * vfs虚拟文件系统工具（文件读取封装）
 */
public class DirectoryMonitor {
	private FileObject fileObject;
	private FileSystemManager fileSystemManager;
	private DefaultFileMonitor fileMonitor;
	private String monitorVolume;
	private String monitorFilePath;
	private String backupVolume;
	private String backupFilePath;
	private boolean recursive;
	private long delay;

	public DirectoryMonitor() {

	}

	/**
	 * destroy
	 */
	public void destroy() {
		fileMonitor.stop();
		fileSystemManager.closeFileSystem(fileObject.getFileSystem());
	}

	private String getWindowsPath(FileName fileName) {
		return org.springframework.util.StringUtils.replace(fileName.getPath(),
				"/", "\\");
	}

	/**
	 * init
	 */
	public void init() {
		try {
			fileSystemManager = VFS.getManager();
			fileObject = fileSystemManager.resolveFile(monitorVolume
					+ monitorFilePath);
		} catch (FileSystemException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		fileMonitor = new DefaultFileMonitor(new FileListener() {
			public void fileCreated(FileChangeEvent fileChangeEvent)
					throws Exception {
				FileObject file = fileChangeEvent.getFile();
				String winPath = monitorVolume + getWindowsPath(file.getName());
				String bakPath = org.apache.commons.lang.StringUtils
						.removeStart(file.getName().getPath(), monitorFilePath);
				File sourceFile = new File(winPath);
				File bakFile = new File(backupVolume + backupFilePath + "/"
						+ bakPath);
				if (!sourceFile.isDirectory()) {
					org.apache.commons.io.FileUtils.copyFile(sourceFile,
							bakFile);
				} else {
					org.apache.commons.io.FileUtils.copyDirectory(sourceFile,
							bakFile, true);
				}
			}

			public void fileDeleted(FileChangeEvent fileChangeEvent)
					throws Exception {
				FileObject file = fileChangeEvent.getFile();
				String bakPath = org.apache.commons.lang.StringUtils
						.removeStart(file.getName().getPath(), monitorFilePath);
				File bakFile = new File(backupVolume + backupFilePath + "/"
						+ bakPath);
				if (bakFile.exists()) {
					bakFile.delete();
				}
			}

			public void fileChanged(FileChangeEvent fileChangeEvent)
					throws Exception {
				FileObject file = fileChangeEvent.getFile();
				String winPath = monitorVolume + getWindowsPath(file.getName());
				String bakPath = org.apache.commons.lang.StringUtils
						.removeStart(file.getName().getPath(), monitorFilePath);
				File sourceFile = new File(winPath);
				File bakFile = new File(backupVolume + backupFilePath + "/"
						+ bakPath);
				if (!sourceFile.isDirectory()) {
					org.apache.commons.io.FileUtils.copyFile(sourceFile,
							bakFile);
				} else {
					org.apache.commons.io.FileUtils.copyDirectory(sourceFile,
							bakFile, true);
				}
			}
		});
		fileMonitor.addFile(fileObject);
		fileMonitor.setRecursive(recursive);
		fileMonitor.setDelay(delay);
		fileMonitor.start();
	}

	public String getBackupFilePath() {
		return backupFilePath;
	}

	public String getBackupVolume() {
		return backupVolume;
	}

	public long getDelay() {
		return delay;
	}

	public boolean isRecursive() {

		return recursive;
	}

	public String getMonitorFilePath() {
		return monitorFilePath;
	}

	public String getMonitorVolume() {
		return monitorVolume;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void setBackupVolume(String backupVolume) {
		this.backupVolume = backupVolume;
	}

	public void setBackupFilePath(String backupFilePath) {
		this.backupFilePath = backupFilePath;
	}

	public void setMonitorVolume(String monitorVolume) {
		this.monitorVolume = monitorVolume;
	}

	public void setMonitorFilePath(String monitorFilePath) {
		this.monitorFilePath = monitorFilePath;
	}

	public void setRecursive(boolean recursive) {

		this.recursive = recursive;
	}
}
