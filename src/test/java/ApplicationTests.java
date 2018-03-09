import com.maxlong.study.datasource.DynamicDataSource;
import com.maxlong.study.enums.DatabaseType;
import com.maxlong.study.mapper.AccBusiWithdrawJnlMapper;
import com.maxlong.study.model.AccBusiWithdrawJnl;
import com.maxlong.study.service.AccBusiWithdrawJnlService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/4/25 11:27
 * 类说明
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.maxlong.study.ApplicationMain.class)
public class ApplicationTests {

    @Resource
    public AccBusiWithdrawJnlService accBusiWithdrawJnlService;

    @Resource
    public AccBusiWithdrawJnlMapper accBusiWithdrawJnlMapper;

    @Autowired
    private Environment env;

    @Test
    public void test_call() {
        DynamicDataSource.setDatabaseType(DatabaseType.PAY);
        AccBusiWithdrawJnl accBusiWithdrawJnl = accBusiWithdrawJnlService.getDataById("17041102000100090001");
        log.info(accBusiWithdrawJnl.toString());
    }

    @Test
    public void test_call2() {
        DynamicDataSource.setDatabaseType(DatabaseType.ACQ);
        AccBusiWithdrawJnl accBusiWithdrawJnl = accBusiWithdrawJnlMapper.selectByBusiSerial("17041102000100090001");
        log.info(accBusiWithdrawJnl.toString());
    }

    @Test
    public void test_call3() {
        String j = env.getProperty("datasource.hikari.com.minIdle");
        System.out.println("++++++++++++++++++" + j);
        int i = Integer.valueOf(j);
        System.out.println("------------------" + i);

    }

}
