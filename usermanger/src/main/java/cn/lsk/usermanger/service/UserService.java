package cn.lsk.usermanger.service;

import cn.lsk.usermanger.pojo.User;
import cn.lsk.usermanger.vo.EasyUIPage;

public interface UserService {

	public EasyUIPage queryEasyUIPage(Integer pageNum, Integer pageSize);

	public boolean addUser(User user);

	public boolean deleteUser(Long[] ids);
	
}
