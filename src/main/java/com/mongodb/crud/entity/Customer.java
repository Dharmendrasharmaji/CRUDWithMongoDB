package com.mongodb.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Customer")
public class Customer {

    @Id
    private UUID id;

    private String name;
    private String age;
    private String mobileNum;
    private String email;

}
