package com.kingmed.dp.modules.sys.enums;

public enum MenuEnum {

	STR_IS_NULL("STR_IS_NULL", "-"),//空字符替代
	ROOT_MENU("ROOT_MENU", "我的菜单"),
	ROOT_MENU_PARENT_ID("ROOT_MENU_ID", "00"),
	PARENT_IDS_SEPARATOR("PARENT_IDS_SEPARATOR", ";"),
	NUMBER_ONE("NUMBER_ONE", "1");
    
    private MenuEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    
    private String code;
    
    private String value;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getNameByCode(String code) {
        for (MenuEnum menuEnum : MenuEnum.values()) {
            if (code.equals(menuEnum.getCode())) {
                return menuEnum.getValue();
            }
        }
        return code;
    }
	
}
