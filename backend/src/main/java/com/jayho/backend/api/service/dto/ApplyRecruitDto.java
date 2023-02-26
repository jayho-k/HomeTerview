package com.jayho.backend.api.service.dto;

import com.jayho.backend.db.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRecruitDto {


    private int result;
    private Apply apply;

}
