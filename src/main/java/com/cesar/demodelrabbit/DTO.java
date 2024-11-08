package com.cesar.demodelrabbit;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class DTO implements Serializable {

    private String title;
    private String content;
    private String id;

}
