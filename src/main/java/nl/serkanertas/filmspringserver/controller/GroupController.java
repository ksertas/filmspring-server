package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;
}
