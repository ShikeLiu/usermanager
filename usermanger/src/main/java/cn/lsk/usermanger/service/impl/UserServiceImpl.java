package cn.lsk.usermanger.service.impl;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.lsk.usermanger.mapper.UserMapper;
import cn.lsk.usermanger.pojo.User;
import cn.lsk.usermanger.service.UserService;
import cn.lsk.usermanger.vo.EasyUIPage;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public EasyUIPage queryEasyUIPage(Integer pageNum, Integer pageSize) {
		EasyUIPage page = new EasyUIPage();
		PageHelper.startPage(pageNum,pageSize);
		List<User> users = userMapper.queryAll();
		PageInfo<User> pageInfo = new PageInfo<>(users);
		page.setRows(users);
		page.setTotal(pageInfo.getTotal());
		return page;
	}

	@Override
	public boolean addUser(User user) {
		Integer resNum = userMapper.addUser(user);
		if(resNum==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(Long[] ids) {
		Integer resNum = userMapper.deleteUser(ids);
		if(resNum>0){
			return true;
		}
		return false;
	}
	
}
