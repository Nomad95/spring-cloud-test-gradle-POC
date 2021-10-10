package com.igor.springcontracttest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@AllArgsConstructor
@Data
public class Result implements Serializable {
    private Boolean isEven;
}
