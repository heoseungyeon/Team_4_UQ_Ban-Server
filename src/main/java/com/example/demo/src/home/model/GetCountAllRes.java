package com.example.demo.src.home.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCountAllRes {
    private int userIdx;
    private String userName;
    private int counts;
}
