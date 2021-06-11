package com.show.sign.controller;

import com.show.sign.service.MenuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.annotation.Resource;




@RestController
@RequestMapping("/menu")
public class MenuController {
 
	@Resource
	private MenuService menuService;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getMenu")
	public @ResponseBody Map getMenu() {
		return menuService.getMenu();
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/getMaterial")
	public @ResponseBody Map getMaterial(String type,String page,String limit) {
		return menuService.getMaterial(type,page,limit);
	}
	

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/setMenu")
	public @ResponseBody Map setMenu(String menu) {
		System.out.println(menu);
		return menuService.setMenu(menu);
	}
	
	//public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";  
	 
}