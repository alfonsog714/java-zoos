package local.alfonso.zoo.controllers;

import local.alfonso.zoo.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZooController {
    @Autowired
    private ZooService zooService;

    @GetMapping(value = "/animalcount", produces = {"application/json"})
    public ResponseEntity<?> getCountAnimalsInZoos()
    {
        return new ResponseEntity<>(zooService.getCountAnimalsInZoos(), HttpStatus.OK);
    } // working 
}
