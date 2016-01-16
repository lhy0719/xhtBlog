package com.lhy.commons.encrypt;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
public interface IEncryptService {
	enum LicenseType{developer,user}
	
	@SuppressWarnings("serial")
	class License implements Serializable{
		private String licenseID;
		private String ipAddress;
		private LicenseType licenseType;
		private Date stopTime;
		public String getLicenseID() {
			return licenseID;
		}
		public void setLicenseID(String licenseID) {
			this.licenseID = licenseID;
		}
		public String getIpAddress() {
			return ipAddress;
		}
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}
		public LicenseType getLicenseType() {
			return licenseType;
		}
		public void setLicenseType(LicenseType licenseType) {
			this.licenseType = licenseType;
		}
		public Date getStopTime() {
			return stopTime;
		}
		public void setStopTime(Date stopTime) {
			this.stopTime = stopTime;
		}
	}
	License getLicense(File licenseFile, String ipAddress);
	File createLicenseFile(License license, String licenseFilePath);
}
