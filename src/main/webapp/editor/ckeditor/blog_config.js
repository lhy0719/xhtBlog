/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	config.skin='kama_4.4.7';
	config.smiley_path=webAppBasePath+'/editor/ckeditor/plugins/smiley/images/';
	//config.width=740;
	config.height=450;
	config.resize_enabled=false;
	config.language='zh-cn';
	config.image_previewText='预览';	
	config.filebrowserWindowWidth=400;
	config.filebrowserWindowHeight=300;
	config.filebrowserBrowseUrl=webAppBasePath+'/user/ckeditor/browserFile?type=file';
	config.filebrowserFlashBrowseUrl=webAppBasePath+'/user/ckeditor/browserFile?type=flash';
	config.filebrowserImageBrowseLinkUrl=webAppBasePath+'/user/ckeditor/browserFile?type=image';
	config.filebrowserImageBrowseUrl=webAppBasePath+'/user/ckeditor/browserFile?type=image';
	config.filebrowserUploadUrl=webAppBasePath+'/user/ckeditor/uploadFile?type=file';	
	config.filebrowserFlashUploadUrl=webAppBasePath+'/user/ckeditor/uploadFile?type=flash';	
	config.filebrowserImageUploadUrl=webAppBasePath+'/user/ckeditor/uploadFile?type=image';
	config.toolbar = [
    	{ name: 'clipboard', groups: [ 'clipboard', 'undo' ], items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
    	{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ], items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat' ] },
    	{ name: 'links', items: [ 'Link', 'Unlink'] },
    	{ name: 'insert', items: [ 'Image', 'Table'] },
    	{ name: 'styles', items: ['Font', 'FontSize' ] },
    	{ name: 'colors', items: [ 'TextColor'] }
	];
	config.removeButtons='Source,Save,NewPage,Templates,Iframe,About';
	config.toolbarCanCollapse = false;
};
