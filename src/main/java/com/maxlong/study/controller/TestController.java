package com.maxlong.study.controller;

import com.github.pagehelper.PageInfo;
import com.maxlong.study.model.AccBusiWithdrawJnl;
import com.maxlong.study.service.AccBusiWithdrawJnlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/2/13 13:57
 * 类说明
 */
@Slf4j
//@Controller
public class TestController {

    @Autowired
    private AccBusiWithdrawJnlService accBusiWithdrawJnlService;


    @RequestMapping(value = {"/getdata"})
    @ResponseBody
    public AccBusiWithdrawJnl getdata() {
        AccBusiWithdrawJnl accBusiWithdrawJnl = accBusiWithdrawJnlService.getDataById("16122102000100010001");
        return accBusiWithdrawJnl;
    }

    @RequestMapping(value = {"/getAll"})
    @ResponseBody
    public int getAll() {
        List<AccBusiWithdrawJnl> list = accBusiWithdrawJnlService.getAll();
        return list.size();
    }

    @RequestMapping(value = {"/queryJnl"})
    @ResponseBody
    public PageInfo queryJnl(HttpSession session) {
        session.getAttribute("");
        AccBusiWithdrawJnl accBusiWithdrawJnl = new AccBusiWithdrawJnl();
        accBusiWithdrawJnl.setPage(1);
        accBusiWithdrawJnl.setRows(10);
        List<AccBusiWithdrawJnl> jnlList = accBusiWithdrawJnlService.queryJnl(accBusiWithdrawJnl);
        return new PageInfo<AccBusiWithdrawJnl>(jnlList);
    }


    @RequestMapping(value = {"/getbotton"})
    public String getbotton()    {
        return "botton";
    }

}
