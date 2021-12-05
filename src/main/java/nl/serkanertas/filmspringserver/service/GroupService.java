package nl.serkanertas.filmspringserver.service;

import nl.serkanertas.filmspringserver.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;



}
