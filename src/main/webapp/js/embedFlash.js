
/**
 * 加载Flash  普通方法
 */
function initFlash(flashID,domNode, showLaysers){
	var swfVersionStr = "11.1.0";
	var xiSwfUrlStr;
	var flashvars = {
		mapconfigUrl:webAppBasePath+"/user/emergency/meta/getMapConfig",
		showLaysers:showLaysers,
		jsPrefix:flashID
	};
	var params = {
			wmode:"opaque"//"transparent"|"opaque"|"window"
	};
	params.quality = "high";
	params.bgcolor = "#ffffff";
	params.allowscriptaccess = "sameDomain";
	params.allowfullscreen = "true";
	var attributes = {};
	attributes.id = flashID;
	attributes.name = "zxbPlatform";
	attributes.align = "middle";
	swfobject.embedSWF(webAppBasePath+"/flex/emergency.swf", domNode, 
	    "100%", "100%", 
	    swfVersionStr, xiSwfUrlStr, 
	    flashvars, params, attributes);
	// JavaScript enabled so display the flashContent div in case it is not replaced with a swf object.
	//swfobject.createCSS("#flashContent", "display:block;text-align:left;");
}

function initChrismas(flashID,domNode){
	var swfVersionStr = "11.1.0";
	var xiSwfUrlStr;
	var flashvars = {
		jsPrefix:flashID
	};
	var params = {
			wmode:"opaque"//"transparent"|"opaque"|"window"
	};
	params.quality = "high";
	params.bgcolor = "#ffffff";
	params.allowscriptaccess = "sameDomain";
	params.allowfullscreen = "true";
	var attributes = {};
	attributes.id = flashID;
	attributes.name = "zxbPlatform";
	attributes.align = "middle";
	swfobject.embedSWF(webAppBasePath+"/flex/chrismas.swf", domNode, 
	    "100%", "100%", 
	    swfVersionStr, xiSwfUrlStr, 
	    flashvars, params, attributes);
	// JavaScript enabled so display the flashContent div in case it is not replaced with a swf object.
	swfobject.createCSS("#flashContent", "display:block;text-align:left;");
}