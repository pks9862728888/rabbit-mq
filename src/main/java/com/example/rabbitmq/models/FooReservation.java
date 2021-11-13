package com.example.rabbitmq.models;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
public class FooReservation implements Serializable {

    private long id;
    private String name;

}
