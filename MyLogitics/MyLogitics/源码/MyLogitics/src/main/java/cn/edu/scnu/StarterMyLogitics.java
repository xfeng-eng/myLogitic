package cn.edu.scnu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("cn.edu.scnu.mapper")//扫描包，创建bean，创建mapper对象，在service中Autowired将对象自动注入
public class StarterMyLogitics {
	public static void main(String[] args){
		SpringApplication.run(StarterMyLogitics.class, args);
	}  
}
