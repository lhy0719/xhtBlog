package com.lhy.web.blog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



import com.lhy.modules.blog.domain.CmsArticle;
import com.lhy.modules.blog.domain.CmsTopic;
import com.lhy.modules.blog.service.ICmsManager;
import com.lhy.modules.system.SystemConstants;
import com.lhy.web.WebConstants;


@Controller("blogManagerController")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@RequestMapping(value = "/blog")
public class BlogManagerController {
	private static final String viewPath="blog/blogManagerControllerView";
	private static final String templateKey="templateKey";
	private enum SubmitType{add,update}
	@Value("#{"+SystemConstants.CONFIG_CmsManager_ID+"}")
	private ICmsManager cmsManager;
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['com.lhy.web.blog.BlogManagerController.articleImageUploadPath']}")
	private Resource articleImageUploadPath;
	private File uploadPath;
	public static final String INDEX_TOPICMAP_KEY="indexTopicMap";
	public static final String TOPIC_ARTICLELIST_KEY="articleList";
	public static final String CMSARTICLE_KEY="cmsArticle";
	public static final String CMSTopic_KEY="cmsTopic";
	public static final String CMSTopicTemplateName_KEY="templateName";
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['com.lhy.web.blog.CmsController.cmsIndexPageConfig']}")
	private String cmsIndexPageConfig;
	@Value("#{"+SystemConstants.CONFIG_SysConfig_ID+"['com.lhy.web.blog.CmsController.defaultRowCount']}")
	private int defaultRowCount;
	@RequestMapping(value = "/findArticle/{topicName}", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView findArticle(@PathVariable("topicName") String topicName,
			@RequestParam(value="pageNum",defaultValue="1",required=false) int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="templateName",defaultValue="public/articleListData",required=false) String templateName) {
		ModelAndView mv = new ModelAndView();
		List<CmsArticle> articleList=this.cmsManager.findArticleByTopicName(topicName,pageNum,pageSize);
		Long totalCount=this.cmsManager.getCmsArticleCount(topicName);
		mv.addObject(TOPIC_ARTICLELIST_KEY,articleList);
		mv.addObject(WebConstants.Pagination.Current_pageNum,pageNum);
		mv.addObject(WebConstants.Pagination.PageSize,pageSize);
		mv.addObject(WebConstants.Pagination.TotalCount,totalCount);
		CmsTopic cmsTopic=this.cmsManager.getCmsTopicByName(topicName);
		mv.addObject(CMSTopic_KEY,cmsTopic);		
		mv.addObject(CMSTopicTemplateName_KEY,templateName);		
		long totalPageCount=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
		mv.addObject(WebConstants.Pagination.TotalPageCount,totalPageCount);
		mv.setViewName(templateName);
		return mv;
	}
	@RequestMapping(value = "/getArticle/{articleOID}", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView getArticle(@PathVariable("articleOID") String articleOID,@RequestParam(value="templateName",defaultValue="public/cmsArticle",required=false) String templateName) {
		ModelAndView mv = new ModelAndView();
		CmsArticle article=this.cmsManager.getCmsArticle(articleOID);
		mv.addObject(CMSARTICLE_KEY,article);
		mv.setViewName(templateName);
		return mv;
	}
	@RequestMapping(value = "/getCmsTopicByName/{topicName}", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView getCmsTopicByName(@PathVariable("topicName") String topicName,@RequestParam(value="templateName",defaultValue="public/cmsTopic",required=false) String templateName) {
		ModelAndView mv = new ModelAndView();
		CmsTopic cmsTopic=this.cmsManager.getCmsTopicByName(topicName);
		mv.addObject(CMSTopic_KEY,cmsTopic);
		mv.setViewName(templateName);
		return mv;
	}
	@RequestMapping(value = "/index", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView index(@RequestParam(value="templateName",defaultValue="user/homeTopics",required=false) String templateName) {
		ModelAndView mv = new ModelAndView();
		JSONObject jsonObject=JSONObject.fromObject(cmsIndexPageConfig);
		JSONArray jsonArray=jsonObject.getJSONArray("cmsArticle");
		Iterator<?> ite=jsonArray.iterator();
		Map<String,Integer> topicNames=new HashMap<String,Integer>();
		while (ite.hasNext()){
			JSONObject topicObj=(JSONObject)ite.next();
			String topicName=topicObj.getString("topicName");
			Integer rowCount=topicObj.containsKey("rowCount")?topicObj.getInt("rowCount"):defaultRowCount;
			topicNames.put(topicName, rowCount);
		}
		Map<String,List<CmsArticle>> indexTopicMap=this.cmsManager.findIndexCmsArticles(topicNames);
		mv.addObject(INDEX_TOPICMAP_KEY, indexTopicMap);		
		mv.setViewName(StringUtils.isBlank(templateName)?jsonObject.getString("defaultTemplateName"):templateName);
		return mv;
	}
	@RequestMapping(value = "/searchArticle", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView searchArticle(@RequestParam("queryParam") String queryParam,
			@RequestParam(value="pageNum",defaultValue="1",required=false) int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="templateName",defaultValue="public/articleSearch",required=false) String templateName) {
		ModelAndView mv = new ModelAndView();
		List<CmsArticle> articleList=this.cmsManager.searchArticle(queryParam,pageNum,pageSize);
		Long totalCount=this.cmsManager.searchArticleCount(queryParam);
		mv.addObject(TOPIC_ARTICLELIST_KEY,articleList);
		mv.addObject(WebConstants.Pagination.Current_pageNum,pageNum);
		mv.addObject(WebConstants.Pagination.PageSize,pageSize);
		mv.addObject(WebConstants.Pagination.TotalCount,totalCount);
		mv.addObject(CMSTopicTemplateName_KEY,templateName);		
		long totalPageCount=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
		mv.addObject(WebConstants.Pagination.TotalPageCount,totalPageCount);
		mv.setViewName(templateName);
		return mv;
	}
	@PostConstruct
	public void init() throws IOException{
		uploadPath = this.articleImageUploadPath.getFile();		
		if (!uploadPath.exists()){
			uploadPath.mkdir();
		}
	}
	@RequestMapping(value = "/findCmsArticle", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView findCmsArticle(@RequestParam("topicName") String topicName,@RequestParam(value="page",defaultValue="1",required=false) int pageNumber,@RequestParam(value="rows",defaultValue="10",required=false) int pageSize) {
		ModelAndView mv = new ModelAndView();
		List<CmsArticle> articleList=this.cmsManager.findArticleByTopicName(topicName,pageNumber,pageSize);
		Long totalCount=this.cmsManager.getCmsArticleCount(topicName);
		mv.addObject("totalCount",totalCount);
		mv.addObject("articleList",articleList);
		mv.addObject(templateKey,"articleList");
		mv.setViewName(viewPath);
		return mv;
	}
	@RequestMapping(value = "/findChildTopic", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView findChildTopic(@RequestParam(defaultValue="",value="topicName") String topicName) {
		ModelAndView mv = new ModelAndView();		
		List<CmsTopic> topicList=this.cmsManager.findChildTopic(topicName);
		mv.addObject("topicList", topicList);
		mv.addObject(templateKey,"topicList");
		mv.setViewName(viewPath);
		return mv;
	}
	@RequestMapping(value = "/addOrUpdateCmsTopic", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView addOrUpdateCmsTopic(@RequestParam(defaultValue="add",value="submitType") String submitType,CmsTopic cmsTopic) {
		ModelAndView mv = new ModelAndView();		
		SubmitType type=SubmitType.valueOf(submitType);
		switch (type){
		case add:
			String parentName=cmsTopic.getParentName();
			this.cmsManager.addCmsTopic(cmsTopic,parentName);
			break;
		case update:
			this.cmsManager.updateCmsTopic(cmsTopic);
			break;
		}		
		mv.addObject(templateKey,"addOrUpdateCmsTopic");
		mv.setViewName(viewPath);
		return mv;
	}
	@RequestMapping(value = "/getCmsTopic", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView getCmsTopic(@RequestParam("topicOID") String topicOID) {
		ModelAndView mv = new ModelAndView();		
		CmsTopic cmsTopic=this.cmsManager.getCmsTopic(topicOID);
		mv.addObject("cmsTopic", cmsTopic);
		mv.addObject(templateKey,"cmsTopic");
		mv.setViewName(viewPath);
		return mv;
	}
	@RequestMapping(value = "/addOrUpdateCmsArticle", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView addOrUpdateCmsArticle(@RequestParam(defaultValue="add",value="submitType") String submitType,CmsArticle cmsArticle,@RequestParam(required=false,value="imageFile")MultipartFile imageFile) throws IOException {
		ModelAndView mv = new ModelAndView();	
		if (imageFile!=null&&!imageFile.isEmpty()){
			String extName=FilenameUtils.getExtension(imageFile.getOriginalFilename());
			String fileName= UUIDGenerator.buildSessionFactoryUniqueIdentifierGenerator().generate(null, null) +"."+extName;
			String absFilePath=this.uploadPath.getAbsolutePath()+File.separator+fileName;
			InputStream input = imageFile.getInputStream();
			OutputStream output=new FileOutputStream(absFilePath);
			IOUtils.copy(input, output);
			input.close();
			output.close();
			cmsArticle.setImageContentType(imageFile.getContentType());
			cmsArticle.setImageFileName(fileName);
			cmsArticle.setImageFilePath(articleImageUploadPath.getFilename());
		}
		SubmitType type=SubmitType.valueOf(submitType);
		switch (type){
		case add:
			String topicName=cmsArticle.getTopicName();
			this.cmsManager.addCmsArticle(cmsArticle,topicName);
			break;
		case update:
			this.cmsManager.updateCmsArticle(cmsArticle);
			break;
		}		
		mv.addObject(templateKey,"addOrUpdateCmsArticle");
		mv.setViewName(viewPath);
		return mv;
	}
	@RequestMapping(value = "/deleteCmsArticle", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView deleteCmsArticle(@RequestParam(value="articleOIDS") String articleOIDS) {
		ModelAndView mv = new ModelAndView();		
		String[] articleOID=StringUtils.split(articleOIDS, ",");
		this.cmsManager.deleteCmsArticle(articleOID);
		mv.addObject(templateKey,"deleteCmsArticle");
		mv.setViewName(viewPath);
		return mv;
	}
	@RequestMapping(value = "/deleteCmsTopic", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView deleteCmsTopic(@RequestParam(value="topicName") String topicName) {
		ModelAndView mv = new ModelAndView();			
		this.cmsManager.deleteCmsTopic(topicName);
		mv.addObject(templateKey,"deleteCmsTopic");
		mv.setViewName(viewPath);
		return mv;
	}
	@RequestMapping(value = "/getCmsArticle", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView getCmsArticle(@RequestParam(value="articleOID") String articleOID) {
		ModelAndView mv = new ModelAndView();		
		CmsArticle cmsArticle=this.cmsManager.getCmsArticle(articleOID);
		mv.addObject("cmsArticle", cmsArticle);
		mv.addObject(templateKey,"cmsArticle");
		mv.setViewName(viewPath);
		return mv;
	}
	@RequestMapping(value = "/isTopicNameExist", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView isTopicNameExist(@RequestParam(value="topicName") String topicName) {
		ModelAndView mv = new ModelAndView();			
		Boolean isTopicNameExist=this.cmsManager.isTopicNameExist(topicName);
		mv.addObject("isTopicNameExist", isTopicNameExist);
		mv.addObject(templateKey,"isTopicNameExist");
		mv.setViewName(viewPath);
		return mv;
	}
}
