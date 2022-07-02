package cn.edu.scnu.aop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scnu.mapper.AuthorityMapper;
@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	private AuthorityMapper authorityMapper;
	public List<String> getAPI(String tokenName) {
		 
		return authorityMapper.getAPI(tokenName);
	}

}
