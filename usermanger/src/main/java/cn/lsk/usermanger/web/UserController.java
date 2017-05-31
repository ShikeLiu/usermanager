package cn.lsk.usermanger.web;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lsk.usermanger.pojo.User;
import cn.lsk.usermanger.service.UserService;
import cn.lsk.usermanger.service.impl.UserServiceImpl;
import cn.lsk.usermanger.vo.EasyUIPage;



@Controller
@RequestMapping(value="user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/page/{pageName}")
	public String toPage(@PathVariable(value="pageName") String pageName){
		return pageName;
	}
	
	@RequestMapping("list")
	@ResponseBody
	public EasyUIPage queryEasyUIPage(@RequestParam(value="page") Integer pageNum, @RequestParam(value="rows") Integer pageSize){
		EasyUIPage page = new EasyUIPage();
		page = userService.queryEasyUIPage(pageNum,pageSize);
		return page;
	}
	
	@RequestMapping(value="saveUser")
	@ResponseBody
	public Map<String, Object> saveUser(User user){
		Map<String, Object> resMap =new HashMap<String, Object>(); ;
		try {
			boolean resBool = this.userService.addUser(user);
			if (resBool) {
				resMap.put("status", 200);
			}else {
				resMap.put("status", 500);
			}
		} catch (Exception e) {
			resMap.put("status", 500);
			e.printStackTrace();
		}
		return resMap;	
	}
	@RequestMapping(value="delete")
	@ResponseBody
	public Map<String, Object> deleteUser(@RequestParam(value="ids") Long[] ids){
		Map<String, Object> resMap =new HashMap<String, Object>(); ;
		try {
			boolean resBool = this.userService.deleteUser(ids);
			if (resBool) {
				resMap.put("status", 200);
			}else {
				resMap.put("status", 500);
			}
		} catch (Exception e) {
			resMap.put("status", 500);
			e.printStackTrace();
		}
		return resMap;
	}
	
	@RequestMapping("list")
	@ResponseBody
	public ModelAndView exportExcel(@RequestParam(value="page") Integer pageNum, @RequestParam(value="rows") Integer pageSize){
		ModelAndView mv = new ModelAndView();
		EasyUIPage page = userService.queryEasyUIPage(pageNum,pageSize);
		mv.setViewName("userExcel");
		mv.addObject("userlist", page.getRows());
		return mv;
	}
}
