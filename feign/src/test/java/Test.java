import com.yyh.application.Application;
import com.yyh.application.service.ApiService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author YanYuHang
 * @create 2019-04-08-16:03
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {

    @Resource
    private ApiService apiService;

    @org.junit.Test
    public void test() {
        System.out.println(apiService.index());
    }
    @org.junit.Test
    public void test1() {
        System.out.println(apiService.index());
    }
}
