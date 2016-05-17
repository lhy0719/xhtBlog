function createCkeditor(id, configFilePath, width, height) {
	return CKEDITOR.replace(id, {
		customConfig : configFilePath,
		width : width,
		height : height
	});
}