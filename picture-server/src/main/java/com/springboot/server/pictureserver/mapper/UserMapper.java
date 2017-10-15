package com.springboot.server.pictureserver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.springboot.server.pictureserver.model.User;


@Mapper
public interface UserMapper {
    
	@Select("select * from user")
	List<User> queryAll();
	
	@Insert("insert into user (username,password,real_name) "
			+ "values(#{username},#{password},#{realName})")
	int add(User user);
	
	@Update("uodate from user set username = #{username},password = #{password},"
			+ "real_name = #{realName} where user_id = #{userId}")
	int update(User user);
	
	@Delete("delete from user where user_id = #{userId}")
	int delete(int userId);
	
	int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> query(User user);

	User findUserByUserName(String username);

	List<String> queryMenusByUid(Integer userId);
}
