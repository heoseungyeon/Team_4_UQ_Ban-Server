package com.example.demo.src.home;

import com.example.demo.src.home.model.*;
import com.example.demo.src.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class HomeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetQuoteRes> getQuotes() {
        String getQuotesQuery = "select quotesId,content from Quotes";
        return this.jdbcTemplate.query(getQuotesQuery,
                (rs,rowNum) -> new GetQuoteRes(
                        rs.getInt("quotesId"),
                        rs.getString("content"))
        );
    }
//
//    public List<GetUserCategoryRes> getUserCategories() {
//    }

    public GetCountRes getCount(int userIdx,int categoryId){
        String getCountQuery = "select Count(*) from Count where userId = ? AND categoryId = ?";
        Object[] getCountParams = new Object[]{userIdx,categoryId};
        return new GetCountRes(this.jdbcTemplate.queryForObject
                (getCountQuery,
                        int.class,
                        getCountParams));
    }

    public List<GetCountAllRes> getCountAll(){
        String getCountQuery = "select a.userId As userId,b.nickname As nickname,COUNT(a.userId) As cnt from Count a LEFT JOIN User b ON a.userId = b.userId group by a.userId order by cnt desc";
        return this.jdbcTemplate.query(getCountQuery,
                (rs,rowNum) -> new GetCountAllRes(
                        rs.getInt("userId"),
                        rs.getString("nickName"),
                        rs.getInt("cnt")
                ));
    }


    public int createCount(int userIdx,int categoryId) {
        System.out.println("userIdx = " + userIdx);
        System.out.println("categoryId = " + categoryId);
        String createCountQuery = "insert into Count (userId, categoryId) VALUES (?,?)";
        Object[] createUserParams = new Object[]{userIdx,categoryId};
        this.jdbcTemplate.update(createCountQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public List<GetCategoryAllRes> getCategoryAll() {
        String getQuotesQuery = "select categoryId, name from Category";
        return this.jdbcTemplate.query(getQuotesQuery,
                (rs,rowNum) -> new GetCategoryAllRes(
                        rs.getInt("categoryId"),
                        rs.getString("name"))
        );
    }
}
