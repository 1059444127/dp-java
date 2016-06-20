/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingmed.dp.gateway.command;

import com.kingmed.dp.gateway.dto.SlideDto;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

/**
 *
 * @author zhengjunjie
 */
public class SlideCommand extends HystrixCommand<String>{
    private final SlideDto slide;
    
    public SlideCommand (SlideDto slide) {
        //super(HystrixCommandGroupKey.Factory.asKey("SlideURL"));
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SlideGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("SlideURL")));
        this.slide = slide;
    }
    
    @Override
    protected String run() throws Exception {
        //TODO 调用LIS 的web service
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
