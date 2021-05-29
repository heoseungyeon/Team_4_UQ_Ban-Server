package com.example.demo.src.home;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.home.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final HomeProvider homeProvider;
    @Autowired
    private final HomeService homeService;
    @Autowired
    private final JwtService jwtService;


    public HomeController(HomeProvider homeProvider, HomeService homeService, JwtService jwtService){
        this.homeProvider = homeProvider;
        this.homeService = homeService;
        this.jwtService = jwtService;
    }

    /**
     * 명언 조회  API
     * [GET] /home/quotes
     * @return BaseResponse<List<GetQuoteRes>>
     */
    @ResponseBody
    @GetMapping("/quotes") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetQuoteRes>> getQuotes() {
        try{
            List<GetQuoteRes> getQuoteRes = homeProvider.getQuotes();
            return new BaseResponse<>(getQuoteRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 전체 카테고 조 회  API
     * [GET] /home/category
     * @return BaseResponse<List<GetQuoteRes>>
     */
    @ResponseBody
    @GetMapping("/category") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetCategoryAllRes>> getCategory() {
        try{
            List<GetCategoryAllRes> getCategoryAllRes = homeProvider.getCategoryAll();
            return new BaseResponse<>(getCategoryAllRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
//    /**
//     * 유저가 현재 설정한 카테고리 조회 API
//     * [GET] /home/usercategory/:userId
//     * @return BaseResponse<GetUserCategoryRes>
//     */
//    // Path-variable
//    @ResponseBody
//    @GetMapping("/usercategory/{userId}") // (GET) 127.0.0.1:9000/app/users/
//    public BaseResponse<List<GetUserCategoryRes>> getUser() int userId) {
//        // Get Users
//        try{
//            List<GetUserCategoryRes> getUserCategoryRes = homeProvider.getUserCatoeries();
//            return new BaseResponse<>(getUserCategoryRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//
//    }

    /**
     * 단일 유저의 단일 카테고리 카운트 조회 API
     * [GET] /home/usercategory/:categoryId
     * @return BaseResponse<GetCountRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/count/{categoryId}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetCountRes> getCountByCategoryId(@PathVariable("categoryId") int categoryId) {
        // Get Users
        try{
            GetCountRes getCountRes = homeProvider.getCountsByCategoryId(categoryId);
            return new BaseResponse<>(getCountRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    @ResponseBody
    @GetMapping("/count") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<List<GetCountAllRes>> getCount() {
        try{
            List<GetCountAllRes> getCountAllRes = homeProvider.getCountAll();
            System.out.println("getCountAllRes = " + getCountAllRes);
            return new BaseResponse<>(getCountAllRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }

    /**
     * 카운트 추가 API
     * [POST] /home/count
     * @return BaseResponse<PostCountRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/count/{categoryId}")
    public BaseResponse<PostCountRes> createCount(@PathVariable("categoryId") int categoryId) {
        try{
            System.out.println("categoryId = " + categoryId);
            PostCountRes postCountRes = homeService.createCount(categoryId);
            return new BaseResponse<>(postCountRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
