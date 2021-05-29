package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from UserInfo";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from UserInfo where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(int userIdx){
        String getUserQuery = "select * from UserInfo where userIdx = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
                getUserParams);
    }
    

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into User (nickname, password) VALUES (?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getNickname(), postUserReq.getPassword()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update UserInfo set userName = ? where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserIdx()};
        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select userIdx, password,email,userName,ID from UserInfo where ID = ?";
        String getPwdParams = postLoginReq.getId();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userIdx"),
                        rs.getString("ID"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("email")
                ),
                getPwdParams
                );

    }

    /**
     * get user category
     */
    public GetCategory getCategory(int userId) {
        String query = "select C.categoryId, name from UserCategory " +
                "    join Category C on UserCategory.categoryId = C.categoryId where userid =? and UserCategory.status = 'Y'";
        List<Cate> cateList = this.jdbcTemplate.query(query,
                (rs, rowNum) -> new Cate(
                        rs.getInt("categoryId"),
                        rs.getString("name")
                ),
                userId
        );
        return new GetCategory(cateList);
    }

    /**
     * add single category
     */
    public void addOneCategory(int userId, int cateNum) {
        String query = "insert into UserCategory(userId, categoryId) VALUES (?,?)";
        Object[] modifyUserNameParams = new Object[]{userId,cateNum};
        this.jdbcTemplate.update(query, modifyUserNameParams);
    }

    /** delete user category
     *
     */

    public void deleteUserCategory(int userId) {
        String query = "update UserCategory set status = 'N' where userId = ?";
        this.jdbcTemplate.update(query, userId);
    }
    /**
     *
     * @param email
     * @return
     */
    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from UserInfo where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);
    }

    public int checkNickname(String nickname) {
        String query = "select exists(select nickname from User where nickname = ?)";
        return this.jdbcTemplate.queryForObject(query, int.class, nickname);
    }

    public int checkCategory(int userId, int cateNum) {
        String query = "select exists(select userId from UserCategory where userId = ? and categoryId = ? and status = 'Y')";
        return this.jdbcTemplate.queryForObject(query, int.class, userId, cateNum);
    }
}
