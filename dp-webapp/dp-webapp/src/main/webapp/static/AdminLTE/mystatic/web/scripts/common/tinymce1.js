$(function() {
	gebo_wysiwg = {
		init : function() {
			$('textarea#wysiwg_full')
					.tinymce(
							{
								// Location of TinyMCE scripm
								script_url : ctx
										+ '/scripts/plugins/tinymce/tiny_mce.js',
								// General options
								theme : "advanced",
								plugins : "table,preview,fullscreen,advlist",
								theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,formatselect,cleanup,|,bullist,numlist,outdent,indent,|,undo,redo,link,unlink,anchor,image,table,|,code,fullscreen,preview|,",
								theme_advanced_toolbar_location : "top",
								theme_advanced_toolbar_align : "left",
								theme_advanced_statusbar_location : "",
								theme_advanced_resizing : false,
								font_size_style_values : "8pt,10px,12pt,14pt,18pt,24pt,36pt",
								forced_root_block : "",
								max_height : 280,
								min_height : 100,
								height : 100,
								init_instance_callback : function() {
									function resizeWidth() {
										document
												.getElementById(tinyMCE.activeEditor.id
														+ '_tbl').style.width = '100%';
									}
									resizeWidth();
									$(window).resize(function() {
										resizeWidth();
									})
								}
							});
			$('textarea#wysiwg_full2')
			.tinymce(
					{
						// Location of TinyMCE scripm
						script_url : ctx
						+ '/scripts/plugins/tinymce/tiny_mce.js',
						// General options
						theme : "advanced",
						plugins : "table,preview,fullscreen,advlist",
						theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,formatselect,cleanup,|,bullist,numlist,outdent,indent,|,undo,redo|,code,fullscreen,preview|,",
						theme_advanced_toolbar_location : "top",
						theme_advanced_toolbar_align : "left",
						theme_advanced_statusbar_location : "",
						theme_advanced_resizing : false,
						font_size_style_values : "8pt,10px,12pt,14pt,18pt,24pt,36pt",
						forced_root_block : "",
						max_height : 280,
						min_height : 200,
						height : 200,
						init_instance_callback : function() {
							function resizeWidth() {
								document
								.getElementById(tinyMCE.activeEditor.id
										+ '_tbl').style.width = '100%';
							}
							resizeWidth();
							$(window).resize(function() {
								resizeWidth();
							})
						}
					});
		}
	};
	gebo_wysiwg.init();
});