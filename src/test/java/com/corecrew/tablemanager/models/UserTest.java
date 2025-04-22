package com.corecrew.tablemanager.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    @Test
    void objectSerializer(){
        System.out.println("hello world");
    }

    @Test
    void objectDeserializer(){
        assertEquals("A".getClass(), String.class);
    }
}