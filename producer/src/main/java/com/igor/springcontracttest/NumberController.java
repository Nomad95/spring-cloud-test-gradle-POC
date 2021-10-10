package com.igor.springcontracttest;

import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class NumberController {

    @PostMapping("/validate/prime-number")
    public Result isNumberPrime(@RequestBody NumberDto number) {
        return number.getNumber() % 2 == 0 ? new Result(true) : new Result(false);
    }
}
