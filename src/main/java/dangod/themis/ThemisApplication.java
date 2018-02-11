package dangod.themis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
@EnableCaching
public class ThemisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThemisApplication.class, args);
	}
}
