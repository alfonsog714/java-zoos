package local.alfonso.zoo.controllers;

import local.alfonso.zoo.model.Zoo;
import local.alfonso.zoo.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ZooController {
    @Autowired
    private ZooService zooService;

    @GetMapping(value = "/zoos/zoos", produces = {"application/json"})
    public ResponseEntity<?> getAllZoos()
    {
        ArrayList<Zoo> rtnZoos = zooService.findAll();
        return new ResponseEntity<>(rtnZoos, HttpStatus.OK);
    }

    @GetMapping(value = "/animalcount", produces = {"application/json"})
    public ResponseEntity<?> getCountAnimalsInZoos()
    {
        return new ResponseEntity<>(zooService.getCountAnimalsInZoos(), HttpStatus.OK);
    } // working

    @DeleteMapping(value = "/delete/{zooid}")
    public ResponseEntity<?> deleteZooById(@PathVariable long zooid)
    {
        zooService.delete(zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
