package com.example.demo_chat.service;

import com.example.demo_chat.model.AppUser;

import java.util.List;

public interface IAppUserService {
    List<AppUser> findAll();
}
