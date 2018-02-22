package dangod.themis;

import com.alibaba.fastjson.JSON;
import dangod.themis.dao.score.MajorRepo;
import dangod.themis.model.po.score.Class;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ThemisApplication.class)
@WebAppConfiguration
public class ThemisApplicationTests {

	@Test
	public void contextLoads() {
	}
}
