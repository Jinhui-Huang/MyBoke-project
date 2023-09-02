package com.itstudy.dao;

import com.itstudy.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * Description: UserDao
 * <br></br>
 * className: UserDao
 * <br></br>
 * packageName: com.itstudy.dao
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/31 12:09
 */
@Mapper
public interface UserDao {
    /*@Select("<script>Select user_email, user_name, user_password, user_img " +
            "from user" +
            "<when test='email != null'> where user_email = " +
            "#{email}</when></script>")
    User getUserByEmail(String email);*/

    /**
     * Description: getUserByEmail 用户登陆用于查询信息
     * @return com.itstudy.domain.User
     * @author jinhui-huang
     * @Date 2023/8/31
     * */
    @Select("Select user_email, user_name, user_password, user_img " +
            "from user where user_email = #{email}")
    User getUserByEmail(String email);

    /**
     * Description: countUserByEmail 查询用户时候存在
     * @return java.lang.Long
     * @author jinhui-huang
     * @Date 2023/8/31
     * */
    @Select("select count(*) from user where user_email = #{email}")
    Long countUserByEmail(String email);

    /**
     * Description: 用户注册
     * @return
     * @author jinhui-huang
     * @Date 2023/8/31
     * */
    @Insert("insert into user(user_email, user_name, user_password, user_img)" +
            "values(#{userEmail}, #{userName}, #{userPassword}, #{userImg})")
    Integer insertUser(User user);

    /**
     * Description: updateUserByEmail 更新用户密码
     * @return java.lang.Integer
     * @author jinhui-huang
     * @Date 2023/8/31
     * */
    @Update("update user set user_password = #{userPassword} where user_email = #{userEmail}")
    Integer updateUserByEmail(User user);
}
