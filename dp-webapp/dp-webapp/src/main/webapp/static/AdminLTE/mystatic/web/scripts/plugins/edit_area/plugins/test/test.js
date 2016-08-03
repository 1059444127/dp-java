/**
 * Plugin designed for test prupose. It add a button (that manage an alert) and a select (that allow to insert tags) in the toolbar.
 * This plugin also disable the "f" key in the editarea, and load a CSS and a JS file
 */  
var EditArea_test= {
	/**
	 * Get called once this file is loaded (editArea still not initialized)
	 *
	 * @return nothing	 
	 */	 	
	
	  	
	init: function(){	
		//	alert("test init: "+ this._someInternalFunction(2, 3));
		editArea.load_css(this.baseURL+"css/test.css");
		EditArea_test.temp_arr = {};
		EditArea_test.temp_arr['ftl_if_eq'] = "<#if (bean.* )?has_content>"
											+" AND  * = {bean.* } "
											+"</#if>";
		EditArea_test.temp_arr['ftl_if_lk'] = "<#if (bean.* )?has_content>"
											+" AND * LIKE '%' {bean.* } '%' "
											+"</#if>";
		EditArea_test.temp_arr['struts_if_eq'] = "<#if (bean.yhzid )?has_content>"
											+" AND G.YHZID LIKE '%' {bean.yhzid } '%'"
											+"</#if>";
		EditArea_test.temp_arr['struts_if_lk'] = "<#if (bean.yhzid )?has_content>"
											+" AND G.YHZID LIKE '%' {bean.yhzid } '%'"
											+"</#if>";											
		//editArea.load_script(this.baseURL+"test2.js");
	}
	/**
	 * Returns the HTML code for a specific control string or false if this plugin doesn't have that control.
	 * A control can be a button, select list or any other HTML item to present in the EditArea user interface.
	 * Language variables such as {$lang_somekey} will also be replaced with contents from
	 * the language packs.
	 * 
	 * @param {string} ctrl_name: the name of the control to add	  
	 * @return HTML code for a specific control or false.
	 * @type string	or boolean
	 */	
	,get_control_html: function(ctrl_name){
		switch(ctrl_name){
			case "test_but":
				// Control id, button img, command
				return parent.editAreaLoader.get_button_html('test_but', 'test.gif', 'test_cmd', false, this.baseURL);
			case "test_select":
				html= "<select id='test_select' onchange='javascript:editArea.execCommand(\"test_select_change\")' fileSpecific='no'>"
					+"			<option value='-1'>{$test_select}</option>"
					+"			<option value='ftl_if_eq'>Fmk if eq </option>"
					+"			<option value='ftl_if_lk'>Fmk if like </option>"
					+"			<option value='struts_if_eq'>Str if eq</option>"
					+"			<option value='struts_if_lk'>Str if like</option>"
					+"		</select>";
				return html;
		}
		return false;
	}
	/**
	 * Get called once EditArea is fully loaded and initialised
	 *	 
	 * @return nothing
	 */	 	 	
	,onload: function(){ 
		//alert("test load");
	}
	
	/**
	 * Is called each time the user touch a keyboard key.
	 *	 
	 * @param (event) e: the keydown event
	 * @return true - pass to next handler in chain, false - stop chain execution
	 * @type boolean	 
	
	,onkeydown: function(e){
		var str= String.fromCharCode(e.keyCode);
		// desactivate the "f" character
		if(str.toLowerCase()=="f"){
			return true;
		}
		return false;
	}
	 */
	/**
	 * Executes a specific command, this function handles plugin commands.
	 *
	 * @param {string} cmd: the name of the command being executed
	 * @param {unknown} param: the parameter of the command	 
	 * @return true - pass to next handler in chain, false - stop chain execution
	 * @type boolean	
	 */
	,execCommand: function(cmd, param){
		// Handle commands
		switch(cmd){
			case "test_select_change":
				var val= document.getElementById("test_select").value;
				if(val!=-1){
					if(EditArea_test.temp_arr[val]){
					parent.editAreaLoader.insertTags(editArea.id, EditArea_test.temp_arr[val], '\r\n');
					}
				
				}
				
				document.getElementById("test_select").options[0].selected=true; 
				return false;
			case "test_cmd":
				alert("测试插件按键点击");
				return false;
		}
		// Pass to next handler in chain
		return true;
	}
	
	/**
	 * This is just an internal plugin method, prefix all internal methods with a _ character.
	 * The prefix is needed so they doesn't collide with future EditArea callback functions.
	 *
	 * @param {string} a Some arg1.
	 * @param {string} b Some arg2.
	 * @return Some return.
	 * @type unknown
	 */
	,_someInternalFunction : function(a, b) {
		return a+b;
	}
};

// Adds the plugin class to the list of available EditArea plugins
editArea.add_plugin("test", EditArea_test);
