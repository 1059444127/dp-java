/**
 * 验证的input中须有placeholder属性，显示提示内容
 * @param thisform  表单对象，如$("#formid")
 * @param arrData 表单中需要验证的name值，如 [loginName,password],不能是name，id等
 * @returns {Boolean}  返回true表示验证通过
 */
function validate_form(thisform,arrData) {
	with (thisform) {
		if (validate_required(arrData) == false) {
			return false;
		}
	}
	return true;
}

function validate_required(field) {
	for(n in field){
		with (field[n]) {
			if(name == 'email'){
				if(value!=null && value!="" && !isEmail(value)){
					alert("请输入有效的邮件地址!");
					field[n].focus();
					return false;
				}
			}else if(name == 'sort' && value == '0'){
					alert("排序不能为0!");
					field[n].focus();
					return false;
			}else if(value == null || value == "") {
				alert(placeholder);
				field[n].focus();
				return false;
			}
		}
	}
	return true;
}
/**
 * /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/ （@ 前面不带点的 如zhangshan@163.com,abc@sina.com.cn
 * /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/  （@ 前面带点的  如zhangshna.Mr@163.com,abc_Wang.dd@sian.com
 * @param str
 * @returns
 */
function isEmail(str){
    var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;  
    return reg.test(str);
}