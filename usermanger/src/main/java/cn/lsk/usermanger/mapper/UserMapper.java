package cn.lsk.usermanger.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lsk.usermanger.pojo.User;

public interface UserMapper {

	public List<User> queryAll();

	public Integer addUser(User user);

	public Integer deleteUser(@Param("ids")Long[] ids);

}
