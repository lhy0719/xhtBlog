package com.lhy.commons.encrypt.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lhy.commons.CommonsConstants;
import com.lhy.commons.encrypt.IEncryptService;

@Service(CommonsConstants.Commons_EncryptService_ID)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class EncryptService implements IEncryptService {
	private static Logger log=Logger.getLogger(EncryptService.class);	
	private static final String LicenseFileName="platForm.license";
	@Override
	public License getLicense(File licenseFile,String ipAddress) {
		License licFile=null;
		try {
			ObjectInput in = new ObjectInputStream(new FileInputStream(licenseFile));
			licFile=(License)in.readObject();
			if (licFile.getIpAddress().equals(DigestUtils.sha512Hex(ipAddress))&&licFile.getLicenseType().equals(LicenseType.user)){
				licFile.setLicenseType(LicenseType.user);
			}else{
				licFile.setLicenseType(LicenseType.developer);
			}
			in.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}		
		return licFile;
	}

	@Override
	public File createLicenseFile(License license,String licenseFilePath) {
		License licObj=new License();
		licObj.setIpAddress(DigestUtils.sha512Hex(license.getIpAddress()));
		licObj.setLicenseID(license.getLicenseID());
		licObj.setLicenseType(license.getLicenseType());
		licObj.setStopTime(license.getStopTime()==null?DateUtils.addDays(new Date(), 30):license.getStopTime());
		File licenseFile=null;
		try {
			licenseFile=new File(licenseFilePath+File.separator+LicenseFileName);
			ObjectOutput out=new ObjectOutputStream(new FileOutputStream(licenseFile));
			out.writeObject(licObj);
			out.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return licenseFile;
	}

}
