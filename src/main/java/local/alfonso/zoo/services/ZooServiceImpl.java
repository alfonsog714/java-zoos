package local.alfonso.zoo.services;

import local.alfonso.zoo.model.Zoo;
import local.alfonso.zoo.repos.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "zooService")
public class ZooServiceImpl implements ZooService
{
    @Autowired
    private ZooRepository zrepos;

    @Override
    public ArrayList<Zoo> findAll() {
        ArrayList<Zoo> list = new ArrayList<>();
        zrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id) {
        if(zrepos.findById(id).isPresent())
        {
            zrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Zoo with the id " + Long.toString(id) + " does not exist.");
        }

    }
}
