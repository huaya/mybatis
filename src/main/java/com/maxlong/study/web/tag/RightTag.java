package com.maxlong.study.web.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/2/27 17:21
 * 类说明
 */
public class RightTag extends TagSupport {

    private String menuId;

    public int doStartTag() throws JspTagException {
        try {
            StringBuffer sb = new StringBuffer("<script type=\"text/javascript\">\n");
            sb.append("var right_btns=\"ddddddddd\";\n");
            sb.append("alert(right_btns);\n");
            sb.append("</script>\n");
            this.pageContext.getOut().write(sb.toString());
            this.pageContext.getOut().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int doEndTag() throws JspTagException {
        return 6;
    }

    public void release() {
        /*  80 */
        super.release();
        /*     */
    }

    public String getMenuId() {
        /*  84 */
        return this.menuId;
        /*     */
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public static boolean get() {
        return false;
    }

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(1999, 12, 31);
    }
}
