/* 
author : 龙庭伟
date  : 2015-08-10
desc   : 截图面板基础数据，以常量表示。
*/
var ClassSize= (function(){
    var constants = {
        MAX : '485',//截图面板最大值
        MIN : '285',//截图面板最小值
        PICWIDTH : '485',//截图图片的宽度
        PICHEIGHT : '285',//截图图片的高度
		PiCLIMITHEIGHT:document.documentElement.clientHeight-100,//辅助图片限制的高度
		PiCLIMITWIDTH:document.documentElement.clientWidth-350//辅助图片限制的宽度
       };
       return function(){

        this.getConstants = function( name ){
            return constants[name];
        }
    }
})();