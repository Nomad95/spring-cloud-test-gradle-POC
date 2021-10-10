package com.igor.springcontracttest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeerController {

    public static final int LEGAL_AGE = 18;

    @PostMapping("check")
    public BeerGrantStatus check(@RequestBody PersonInfo personInfo) {
        if (personInfo.getAge() >= LEGAL_AGE) {
            return new BeerGrantStatus("OK");
        }

        return new BeerGrantStatus("NOT_OK");
    }
}
