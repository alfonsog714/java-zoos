package local.alfonso.zoo.controllers;

import local.alfonso.zoo.model.Zoo;
import local.alfonso.zoo.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
        return new ResponseEntity<>(HttpStatus.OK); // not working
    }

    @PostMapping(value = "/admin/zoos", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewzoo(@Valid @RequestBody Zoo newZoo)
    {
        newZoo = zooService.save(newZoo);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newZooURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{zooid}").buildAndExpand(newZoo.getZooid()).toUri();

        responseHeaders.setLocation(newZooURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/admin/zoos/{id}")
    public ResponseEntity<?> updateZooById(@RequestBody Zoo updateZoo, @PathVariable long id)
    {
        zooService.update(updateZoo, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
