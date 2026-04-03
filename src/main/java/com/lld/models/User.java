package com.lld.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String emailId;
    private String contactNo;
    private boolean isAdmin;

    public void notify(String message){
       // notification sent to the user
    }
}
