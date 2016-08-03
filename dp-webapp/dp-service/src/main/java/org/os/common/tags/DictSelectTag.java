package org.os.common.tags;

import java.io.IOException;
import java.util.List;
 
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
 
import org.apache.commons.lang3.StringUtils;

import com.kingmed.dp.module.sys.entity.Dict;
import com.kingmed.dp.module.sys.utils.DictUtils;
 /**
  * 自定义数据字典下拉框标签
  * @author zl
  * @date   2016年4月26日下午2:38:58
  */
public class DictSelectTag extends TagSupport  {
 
	private static final long serialVersionUID = 1L;
	/*字典表中的type*/
	private String dictName;
    private boolean defaultValue;
    /*字典表中的value*/
    private String value;
    private String name;
    private String id;
    private String cssClass;
    private String styleClass;
    private String multiple;
    private String onChange;
 
     
     
    public String getCssClass() {
        return cssClass;
    }
 
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
 
    public String getStyleClass() {
        return styleClass;
    }
 
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }
 
    public String getMultiple() {
        return multiple;
    }
 
    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }
 
    public String getOnChange() {
        return onChange;
    }
 
    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getValue() {
        return value;
    }
 
    public void setValue(String value) {
        this.value = value;
    }
 
    public String getDictName() {
        return dictName;
    }
 
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
     
    public boolean isDefaultValue() {
        return defaultValue;
    }
 
    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }
     
     
    @Override
    public int doEndTag() throws JspException{
        List<Dict> dict_list = DictUtils.getDictList(dictName);
        JspWriter out = pageContext.getOut();
        StringBuffer dictSelect = new StringBuffer();
        dictSelect.append("<select name='"+this.getName()+"' id='"+this.getId()+"'");
        if(!StringUtils.isEmpty(this.getCssClass())){
        	dictSelect.append("class=\"" + this.getCssClass() + "\" ");
        }
        if(!StringUtils.isEmpty(this.getStyleClass())){
        	dictSelect.append("style=\"" + this.getStyleClass() + "\" ");
        }
        if(!StringUtils.isEmpty(this.getMultiple())){
        	dictSelect.append("multiple=\"" + this.getMultiple() + "\" ");
        }
        if(!StringUtils.isEmpty(this.getOnChange())){
        	dictSelect.append("onchange=\"" + this.getOnChange() + "\" ");
        }
        dictSelect.append(">");
        if(this.isDefaultValue()){  
        	dictSelect.append("<option value=''>--请选择--</option>");  
        }
        for(Dict dc:dict_list){
            if(dc.getValue().equals(this.getValue())){
            	dictSelect.append("<option value='"+dc.getValue()+"' selected='selected'>");
            }else{
            	dictSelect.append("<option value='"+dc.getValue()+"'>");
            }
            dictSelect.append(dc.getLabel()+"</option>");
        }
        dictSelect.append("</select>");
        try {
            out.write(dictSelect.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new JspException(e);
        }
        return TagSupport.EVAL_PAGE;
    }
     
}
