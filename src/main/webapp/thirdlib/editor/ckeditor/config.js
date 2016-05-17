/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	config.skin='moono_color_1.4.1';
	//config.smiley_path=webAppBasePath+'/editor/ckeditor/plugins/smiley/images/';
	//config.width=740;
	//config.height=250;
	//config.resize_enabled=false;
	//config.language='zh-cn';
	//config.image_previewText='预览';	
	//config.filebrowserWindowWidth=400;
	//config.filebrowserWindowHeight=300;
	//config.filebrowserBrowseUrl=webAppBasePath+'/admin/ckeditor/browserFile?type=file';
	//config.filebrowserFlashBrowseUrl=webAppBasePath+'/admin/ckeditor/browserFile?type=flash';
	//config.filebrowserImageBrowseLinkUrl=webAppBasePath+'/admin/ckeditor/browserFile?type=image';
	//config.filebrowserImageBrowseUrl=webAppBasePath+'/admin/ckeditor/browserFile?type=image';
	//config.filebrowserUploadUrl=webAppBasePath+'/admin/ckeditor/uploadFile?type=file';	
	//config.filebrowserFlashUploadUrl=webAppBasePath+'/admin/ckeditor/uploadFile?type=flash';	
	//config.filebrowserImageUploadUrl=webAppBasePath+'/admin/ckeditor/uploadFile?type=image';
	config.toolbar = [
    	{ name: 'document', groups: [ 'mode', 'document', 'doctools' ], items: [ 'Source', '-', 'Save', 'NewPage', 'Preview', 'Print', '-', 'Templates' ] },
    	{ name: 'clipboard', groups: [ 'clipboard', 'undo' ], items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
    	{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ], items: [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ] },
    	{ name: 'forms', items: [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] },
    	'/',
    	{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ], items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat' ] },
    	{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ], items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl', 'Language' ] },
    	{ name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
    	{ name: 'insert', items: [ 'Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe' ] },
    	'/',
    	{ name: 'styles', items: [ 'Styles', 'Format', 'Font', 'FontSize' ] },
    	{ name: 'colors', items: [ 'TextColor', 'BGColor' ] },
    	{ name: 'tools', items: [ 'Maximize', 'ShowBlocks' ] },
    	{ name: 'others', items: [ '-' ] },
    	{ name: 'about', items: [ 'About' ] }
	];
	config.removeButtons='Source,Save,NewPage,Templates,Iframe,About';
	config.toolbarCanCollapse = false;
};
