package com.example.demo.src.home;

import com.example.demo.src.home.model.GetCountRes;
import com.example.demo.src.home.model.GetQuoteRes;
import com.example.demo.src.home.model.GetUserCategoryRes;
import com.example.demo.src.home.model.PostCountReq;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HomeDao {
    public List<GetQuoteRes> getQuotes() {
    }

    public List<GetUserCategoryRes> getUserCategories() {
    }

    public GetCountRes getCount(int categoryId) {
    }

    public int createCount(PostCountReq postCountReq) {
    }
}
