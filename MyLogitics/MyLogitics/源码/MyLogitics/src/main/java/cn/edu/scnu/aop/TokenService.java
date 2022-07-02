package cn.edu.scnu.aop;

import cn.edu.scnu.entity.Token;


public interface TokenService {

	

	public int insertToken(String userName, String uuid);

	public Token findByTokenName(String userName, String uuid);


}
