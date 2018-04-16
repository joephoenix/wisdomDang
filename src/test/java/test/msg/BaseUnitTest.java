package test.msg;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//让测试运行于Spring环境  
@RunWith(SpringJUnit4ClassRunner.class)
// 引入Spring配置
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class BaseUnitTest {

}
