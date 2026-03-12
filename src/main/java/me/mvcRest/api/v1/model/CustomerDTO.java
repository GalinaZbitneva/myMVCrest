package me.mvcRest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {
   //этот класс отвечает за ту информацию, которая бужет отражаться на странице юрл запроса, здесь нет ID
    //поэтому в postman мы его не увидим, только firstName и lastName
    @JsonProperty("firstName")
    private String firstName;
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;

}
