package cn.edu.scnu.aop;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AuthorityService {
	
	public  List<String> getAPI(String tokenName);
}
