package com.example.agricolaserver.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CardCountDTO {
    private int openJobCount;
    private int hiddenJobCount;
    private int equipmentCount;
    private int openAuxiliaryCount;
    private int hiddenAuxiliaryCount;
}
