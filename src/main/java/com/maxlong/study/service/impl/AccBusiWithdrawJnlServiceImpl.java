package com.maxlong.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.maxlong.study.datasource.DynamicDataSource;
import com.maxlong.study.enums.DatabaseType;
import com.maxlong.study.mapper.AccBusiWithdrawJnlMapper;
import com.maxlong.study.model.AccBusiWithdrawJnl;
import com.maxlong.study.service.AccBusiWithdrawJnlService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/2/13 14:04
 * 类说明
 */
@Repository
public class AccBusiWithdrawJnlServiceImpl implements AccBusiWithdrawJnlService {

    @Resource
    @Lazy
    public AccBusiWithdrawJnlMapper accBusiWithdrawJnlMapper;

    public AccBusiWithdrawJnl getDataById(String busiSerial) {
//        DynamicDataSource.setDatabaseType(DatabaseType.ACQ);
        return accBusiWithdrawJnlMapper.selectByBusiSerial(busiSerial);
    }

    public List<AccBusiWithdrawJnl> getAll() {
        return null;
    }

    public List<AccBusiWithdrawJnl> queryJnl(AccBusiWithdrawJnl accBusiWithdrawJnl) {
        return null;
    }

}
