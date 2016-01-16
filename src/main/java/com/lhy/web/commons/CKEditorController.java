package com.lhy.web.commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lhy.modules.system.SystemConstants;
import com.lhy.modules.system.domain.CommonUser;
import com.lhy.web.WebConstants;

@Controller("CKEditorController")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@RequestMapping(value = "/ckeditor")
@SessionAttributes({WebConstants.LOGIN_USER_KEY})
public class CKEditorController {
	private enum Type{image,flash,file}
	private static final String URL_separator="/";
	private static Logger log=Logger.getLogger(CKEditorController.class);
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['com.lhy.web.editor.CKEditorController.uploadPath']}")
	private Resource editorUploadPath;
	private File uploadPath;
	private String imageFilePath;
	private String uploadFilePath;
	private String flashFilePath;
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['com.lhy.web.editor.CKEditorController.allowImageTypes']}")
	private String allowImageTypes;
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['com.lhy.web.editor.CKEditorController.allowFlashTypes']}")
	private String allowFlashTypes;
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['com.lhy.web.editor.CKEditorController.allowFileTypes']}")
	private String allowFileTypes;
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['com.lhy.web.editor.CKEditorController.noAllowMessage']}")
	private String noAllowMessage;
	@PostConstruct
	public void init() throws IOException{
		uploadPath = this.editorUploadPath.getFile();		
		if (!uploadPath.exists()){
			uploadPath.mkdir();
		}
		this.imageFilePath=uploadPath.getAbsolutePath()+File.separator+Type.image.toString();
		File imagePath=new File(imageFilePath);
		if (!imagePath.exists()){
			imagePath.mkdir();
		}
		this.uploadFilePath=uploadPath.getAbsolutePath()+File.separator+Type.file.toString();
		File filePath=new File(uploadFilePath);
		if (!filePath.exists()){
			filePath.mkdir();
		}
		this.flashFilePath=uploadPath.getAbsolutePath()+File.separator+Type.flash.toString();
		File flashPath=new File(flashFilePath);
		if (!flashPath.exists()){
			flashPath.mkdir();
		}
	}
	@RequestMapping(value = "/uploadFile", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView uploadFile(@RequestParam("upload")MultipartFile uploadFile,@RequestParam("type") String type,
			@RequestParam("CKEditor")String CKEditor,@RequestParam("CKEditorFuncNum")String CKEditorFuncNum,@RequestParam("langCode")String langCode,
			@ModelAttribute(WebConstants.LOGIN_USER_KEY) CommonUser currentUser){
		ModelAndView mv = new ModelAndView();
		Type fileType=Type.valueOf(type);
		Boolean success=false;
		String extName=FilenameUtils.getExtension(uploadFile.getOriginalFilename());
		String fileName= UUIDGenerator.buildSessionFactoryUniqueIdentifierGenerator().generate(null, null) +"."+extName;
		String currentDir=uploadPath.getAbsolutePath()+File.separator+type+File.separator+currentUser.getCommonUserName();
		File dirFile=new File(currentDir);
		if (!dirFile.exists()){
			dirFile.mkdir();
		}
		String filePath=currentDir+File.separator+fileName;		
		switch (fileType){
			case file:
				filePath=this.uploadFilePath+File.separator+currentUser.getCommonUserName()+File.separator+fileName;
				String[] fileTypes=StringUtils.split(this.allowFileTypes, ",");
				success=ArrayUtils.contains(fileTypes, extName);
				if(!success){
					mv.addObject("message",MessageFormat.format(this.noAllowMessage,this.allowFileTypes));
				}				
				break;
			case flash:
				filePath=this.flashFilePath+File.separator+currentUser.getCommonUserName()+File.separator+fileName;
				String[] flashTypes=StringUtils.split(this.allowFlashTypes, ",");
				success=ArrayUtils.contains(flashTypes, extName);
				if(!success){
					mv.addObject("message",MessageFormat.format(this.noAllowMessage,this.allowFlashTypes));
				}	
				break;
			case image:
				filePath=this.imageFilePath+File.separator+currentUser.getCommonUserName()+File.separator+fileName;	
				String[] imageTypes=StringUtils.split(this.allowImageTypes, ",");
				success=ArrayUtils.contains(imageTypes, extName);
				if(!success){
					mv.addObject("message",MessageFormat.format(this.noAllowMessage,this.allowImageTypes));
				}
				break;
		}
		if (success){
			try {
				InputStream input = uploadFile.getInputStream();
				OutputStream output=new FileOutputStream(filePath);
				IOUtils.copy(input, output);
				input.close();
				output.close();
				String url=editorUploadPath.getFilename()+URL_separator+type+URL_separator+currentUser.getCommonUserName()+URL_separator+fileName;
				mv.addObject("url", url);	
				success=true;
			} catch (IOException e) {
				String errorMsg=e.getMessage();
				log.error(errorMsg);
				success=false;
				mv.addObject("message", errorMsg);
			}
		}		
		mv.addObject("CKEditor", CKEditor);
		mv.addObject("CKEditorFuncNum", CKEditorFuncNum);
		mv.addObject("langCode", langCode);
		mv.addObject("success", success);
		mv.setViewName("editor/upload");
		return mv;
	}
	@RequestMapping(value = "/browserFile", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView browserFile(@RequestParam("type") String type,
			@RequestParam("CKEditor")String CKEditor,@RequestParam("CKEditorFuncNum")String CKEditorFuncNum,
			@RequestParam("langCode")String langCode,@ModelAttribute(WebConstants.LOGIN_USER_KEY) CommonUser currentUser){
		ModelAndView mv = new ModelAndView();
		Type fileType=Type.valueOf(type);
		String filePath=this.uploadPath.getAbsolutePath()+File.separator+type+File.separator+currentUser.getCommonUserName();
		File dirFile=new File(filePath);
		if (!dirFile.exists()){
			dirFile.mkdir();
		}
		switch (fileType){
		case file:
			filePath=this.uploadFilePath+File.separator+currentUser.getCommonUserName();
			break;
		case flash:
			filePath=this.flashFilePath+File.separator+currentUser.getCommonUserName();
			break;
		case image:
			filePath=this.imageFilePath+File.separator+currentUser.getCommonUserName();
			break;
		}		
		Iterator<File> files=FileUtils.iterateFiles(new File(filePath), null,false);
		String baseUrl=editorUploadPath.getFilename()+File.separator+type+File.separator+currentUser.getCommonUserName()+File.separator;
		List<EditorFile> fileList=new ArrayList<EditorFile>();
		while (files.hasNext()){
			File file=files.next();
			EditorFile editorFile=new EditorFile();
			editorFile.setFileName(file.getName());
			editorFile.setFilePath(baseUrl+file.getName());
			fileList.add(editorFile);
		}
		mv.addObject("file_list", fileList);
		mv.addObject("CKEditor", CKEditor);
		mv.addObject("CKEditorFuncNum", CKEditorFuncNum);
		mv.addObject("langCode", langCode);
		mv.setViewName("editor/fileManager");
		return mv;
	}
	public class EditorFile{
		private String fileName;
		private String filePath;
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
	}
}
