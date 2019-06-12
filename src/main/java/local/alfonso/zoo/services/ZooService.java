package local.alfonso.zoo.services;

import local.alfonso.zoo.model.Zoo;

import java.util.ArrayList;

public interface ZooService
{
    ArrayList<Zoo> findAll();

    void delete(long id);
}
