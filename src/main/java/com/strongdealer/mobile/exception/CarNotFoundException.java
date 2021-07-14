package com.strongdealer.mobile.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarNotFoundException extends RuntimeException {
    private Long id;
}
