package cn.edu.scnu.mapper;

import java.util.Map;

import cn.edu.scnu.entity.Token;


public interface TokenMapper {

	public Token findByTokenName(Map<String, String> token);

	public int insertToken(Map<String, String> token);



}
