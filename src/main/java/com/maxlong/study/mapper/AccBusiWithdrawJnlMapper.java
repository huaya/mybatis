package com.maxlong.study.mapper;

import com.maxlong.study.model.AccBusiWithdrawJnl;
import com.maxlong.study.util.MyMapper;

import java.util.List;

public interface AccBusiWithdrawJnlMapper {

    AccBusiWithdrawJnl selectByBusiSerial(String busiSerial);

}