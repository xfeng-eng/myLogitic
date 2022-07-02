package cn.edu.scnu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.edu.scnu.entity.Address;
import cn.edu.scnu.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("address")
@Api(tags ="AddressController相关的api")
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@RequestMapping("/findAddressList")
	@ApiOperation(value="查看所有地址",notes="查看所有地址")
	public ModelAndView findAddressList() {
		List<Address> allAddress = addressService.findAddressList();

		//返回的数据
		Map<String,Object> map = new HashMap<String,Object>();
		if(null == allAddress || allAddress.size() ==0){
			map.put("code", "403");
			map.put("message", "失败");
			return new ModelAndView(new MappingJackson2JsonView(),map);	
		}else{
			map.put("code", "200");
			map.put("message", "成功");
			map.put("allAddress",allAddress);
			return new ModelAndView(new MappingJackson2JsonView(),map);
	
		}
	}
}
