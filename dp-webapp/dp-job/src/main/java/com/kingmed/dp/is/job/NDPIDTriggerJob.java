/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.kingmed.dp.is.job;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractIndividualThroughputDataFlowElasticJob;
import com.kingmed.dp.is.util.HttpRequestUtil;
import com.kingmed.dp.modules.sys.service.JobIsParamsService;


@Component
public class NDPIDTriggerJob extends AbstractIndividualThroughputDataFlowElasticJob<Map<String,List<Map<String,Object>>>> {
    
	private static Logger logger = LoggerFactory.getLogger(NDPIDTriggerJob.class);
        
    @Autowired
	private JobIsParamsService jobIsParamsService;
	
    @Override
    public List<Map<String,List<Map<String,Object>>>> fetchData(final JobExecutionMultipleShardingContext context) {
        List<Map<String,List<Map<String,Object>>>> list = jobIsParamsService.findAllList();
        
        return list;
    }
    
    @Override
    public boolean processData(final JobExecutionMultipleShardingContext context, final Map<String,List<Map<String,Object>>> datas) {
    	for(Map<String,Object> data : datas.get("data")){
    		//printContext.printProcessDataMessage(data);
            StringBuffer param = new StringBuffer("");
    		if(!(data.get("user_name") == null||"".equals(data.get("user_name")))){
    			param.append("Username=");
        		param.append(data.get("user_name"));
        		param.append("&");
        		param.append("Password=");
        		param.append(data.get("user_password"));
    		}
    		
        	String result = HttpRequestUtil.sendGet(data.get("url").toString(), param.toString());
    		logger.info("发送url:"+data.get("url")+"返回结果："+result);
        	logger.info("----------------|  任务进行中。。。  |---------------------:" + param);
    	}
    	logger.info("----------------|  一组任务完成  |---------------------" );
        return true;
    }
    
    @Override
    public boolean isStreamingProcess() {
        return false;
    }
}
