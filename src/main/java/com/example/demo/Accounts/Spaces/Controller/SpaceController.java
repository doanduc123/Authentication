package com.example.demo.Accounts.Spaces.Controller;

import com.example.demo.Accounts.Spaces.Models.Space;
import com.example.demo.Accounts.Spaces.Repository.SpaceRepository;
import com.example.demo.Accounts.Spaces.Services.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/spaces")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @Secured("SPACE")
    @GetMapping
    public List<Space> getAllByUser(){
        return spaceService.findAll();
    }
}


