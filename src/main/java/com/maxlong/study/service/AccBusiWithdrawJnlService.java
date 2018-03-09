package com.maxlong.study.service;

import com.maxlong.study.model.AccBusiWithdrawJnl;

import java.util.List;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/2/13 14:02
 * 类说明
 */
public interface AccBusiWithdrawJnlService {
    AccBusiWithdrawJnl getDataById(String busiSerial);

    List<AccBusiWithdrawJnl> getAll();

    List<AccBusiWithdrawJnl> queryJnl(AccBusiWithdrawJnl accBusiWithdrawJnl);
}
