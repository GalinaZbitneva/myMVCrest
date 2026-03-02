package me.mvcRest.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {
   //этот класс отвечает за ту информацию, которая бужет отражаться на странице юрл запроса, здесь нет ID
    //поэтому в postman мы его не увидим, только firstName и lastName
    private String firstName;
    private String lastName;

}
