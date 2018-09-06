package Chaozhou;

import com.zehao.Chaozhou.mapper.TbuserMapper;
import com.zehao.Chaozhou.pojo.Tbuser;
import com.zehao.Chaozhou.utils.DateConverter;
import com.zehao.Chaozhou.utils.Logger;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by MS on 2018/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/spring/applicationContext-dao.xml"})
public class MapperTest {

    @Autowired
    private TbuserMapper tbuserMapper;

    @Test
    public void testSelectUser() {
        Logger.error("Hello Logger");
        List<Tbuser> tbuserList = tbuserMapper.selectByExample(null);
        for (Tbuser tbuser: tbuserList) {
            System.out.println(tbuser);
        }
    }

    @Test
    public void testDateConvert() {
        DateConverter dateConverter = new DateConverter();
        Date date = dateConverter.convert("20151202");
        System.out.println(date);
    }
}
