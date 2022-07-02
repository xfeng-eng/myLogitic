package cn.edu.scnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.scnu.entity.Address;
import cn.edu.scnu.mapper.AddressMapper;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressMapper addressMapper;
	@Override
	public List<Address> findAddressList() {
		return addressMapper.findAddressList();
	}

}
