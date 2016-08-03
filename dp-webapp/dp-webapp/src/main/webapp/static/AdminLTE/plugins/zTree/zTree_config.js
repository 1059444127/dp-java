/**
 * Created by zhanglei on 2016/4/15.
 */

//解决jQuery1.9以后不兼容（$.browser这个api从jQuery1.9开始就正式废除）问题
jQuery.browser={};(function(){jQuery.browser.msie=false; jQuery.browser.version=0;if(navigator.userAgent.match(/MSIE ([0-9]+)./)){ jQuery.browser.msie=true;jQuery.browser.version=RegExp.$1;}})();

/**
 * 复选框setting
 * */
var setting = {
    check: {
        enable: true,
        chkStyle: "checkbox"
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    callback: {
        onClick: checkTreeOnClick
    }
};
/**
 * 无check setting
 * */
var setting1 = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    }
};
//同一个页面需要多个同类型树，而点击事件不同
var setting2 = {
	    data: {
	        simpleData: {
	            enable: true,
	            idKey: "id",
	            pIdKey: "pId",
	            rootPId: 0
	        }
	    }
	};
var setting3 = {
	    data: {
	        simpleData: {
	            enable: true,
	            idKey: "id",
	            pIdKey: "pId",
	            rootPId: 0
	        }
	    }
	};
/**
 * 单选框setting
 * */
var setting4 = {
    check: {
        enable: true,
        chkStyle: "radio",
        radioType: "all"
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    callback: {
        onClick: checkTreeOnClick
    }
};
function checkTreeOnClick(event, treeId, treeNode) {
    treeNode.checked = !treeNode.checked;
    $.fn.zTree.getZTreeObj(treeId).updateNode(treeNode);
}

//是否存在指定函数
function isExitsFunction(funcName) {
    try {
        if (typeof(eval(funcName)) == "function") {
            return true;
        }
    } catch(e) {}
    return false;
}
//是否存在指定变量
function isExitsVariable(variableName) {
    try {
        if (typeof(variableName) == "undefined") {
            //alert("value is undefined");
            return false;
        } else {
            //alert("value is true");
            return true;
        }
    } catch(e) {}
    return false;
}

