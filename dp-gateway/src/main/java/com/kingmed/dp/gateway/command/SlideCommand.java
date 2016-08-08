/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingmed.dp.gateway.command;

import org.apache.log4j.Logger;

import com.kingmed.dp.gateway.client.LISService;
import com.kingmed.dp.gateway.dto.SlideDto;
import com.kingmed.dp.servlet.modules.Gateway;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 *
 * @author zhengjunjie
 */
public class SlideCommand extends HystrixCommand<String>{
	
	private static Logger logger = Logger.getLogger(SlideCommand.class);
	
    private final SlideDto slide;
    
    private LISService lis = LISService.getInstance();
    
    public SlideCommand (SlideDto slide) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SlideGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("SlideURL"))
                /* 配置依赖超时时间,3000毫秒*/ 
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(3000))
        		);
        this.slide = slide;
    }
    
    @Override
    protected String run() throws Exception {
		String ret = lis.sendResultInfo(slide);
		logger.info("上传数字切片返回结果："+ret);
		if(!"0".equals(ret)){
			ret = "-1";
		}
    	return ret;
    }
    
    @Override
    protected String getFallback() {
    	return "Fail";
    }
    
    
    
}
