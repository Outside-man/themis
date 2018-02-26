package dangod.themis.util;

import com.alibaba.fastjson.JSON;
import dangod.themis.dao.score.MajorRepo;
import dangod.themis.model.po.score.Class;
import javassist.bytecode.analysis.Executor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


public class MD5UtilTest {
    @Test
    public void MD5() throws Exception {
        Thread.sleep(1000);
        System.out.println(":start");

        int poolSize = 5;
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);
        List<Future> list = new ArrayList<>();
        for(int i =0;i<5;i++){
            list.add(pool.submit(new test(i)));
        }
        pool.shutdown();
        for(Future future : list){
            future.get();
        }

    }
    @Test
    public void Test() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("name", "yxm");
        CodeGenerate.Generrate("template", "test.ftl", "template/test.html", map);
    }

}
class test implements Callable<Integer>{
    private int status = 0;

    test(int t){
        status = t;
    }

    @Override
    public Integer call() throws Exception {
        for(int i =0;i<10;i++) {
            System.out.println(this.status + ":start");
            Thread.sleep(1000);
        }
        return status;
    }
}