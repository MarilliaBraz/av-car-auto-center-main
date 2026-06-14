package com.ads3.auto_center.core.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDTO {
    private Long id;
    private boolean active;
}