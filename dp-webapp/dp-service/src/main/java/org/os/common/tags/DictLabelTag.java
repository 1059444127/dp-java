package org.os.common.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import com.kingmed.dp.module.sys.utils.DictUtils;
 
 /**
  * 自定义数据字典取的确定值（根据值，直接返回label名称）
  * @author zl
  * @date   2016年4月26日下午2:46:15
  */
public class DictLabelTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	/*字典表中的type*/
	private String dictName = "";
	/*字典表中的value*/
    private String value;
 
    public String getDictName() {
        return dictName;
    }
 
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write(DictUtils.getDictLabel(this.getValue(), this.getDictName(), ""));
            //out.write("，根据type与value查出的label值");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return TagSupport.EVAL_PAGE;
    }
}