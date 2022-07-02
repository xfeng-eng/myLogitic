package cn.edu.scnu.aop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scnu.entity.Token;

import cn.edu.scnu.mapper.TokenMapper;

@Service
public class TokenServiceImpl implements TokenService{
	@Autowired
	private TokenMapper tokenMapper;

	@Override
	public int insertToken(String userName, String uuid) {
		Map<String,String> token = new HashMap<>();
		token.put("tokenName", uuid);
		token.put("loginName",userName);
		return tokenMapper.insertToken(token);
	}
	@Override
	public Token findByTokenName(String userName, String uuid) {
		Map<String,String> token = new HashMap<>();
		//System.out.print("this is findByTokenName serviceImpl:"+userName+"   :"+uuid);
		token.put("tokenName", uuid);
		token.put("loginName",userName);
		return tokenMapper.findByTokenName(token);
	}

}
