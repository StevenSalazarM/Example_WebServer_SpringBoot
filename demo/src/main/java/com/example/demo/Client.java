package com.example.demo;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Client")
public class Client implements Serializable {

    private String id;
    private String name;

    
    public Client() {
    }
    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}