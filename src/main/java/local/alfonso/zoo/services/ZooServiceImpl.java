package local.alfonso.zoo.services;


import local.alfonso.zoo.model.Animal;
import local.alfonso.zoo.model.Telephone;
import local.alfonso.zoo.model.Zoo;
import local.alfonso.zoo.repos.ZooRepository;
import local.alfonso.zoo.views.CountAnimalsInZoo;
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
            zrepos.deleteZooFromZooanimals(id);
            zrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Zoo with the id " + id + " does not exist.");
        }

    }

    @Override
    public ArrayList<CountAnimalsInZoo> getCountAnimalsInZoos() {
        return zrepos.getCountAnimalsInZoo();
    }

    @Transactional
    @Override
    public Zoo save(Zoo zoo)
    {
        Zoo newZoo = new Zoo();

        newZoo.setZooname(zoo.getZooname());

        for (Telephone t : zoo.getTelephones())
        {
            newZoo.getTelephones().add(new Telephone(t.getPhonetype(), t.getPhonenumber(), newZoo));
        }

        for (Animal a : zoo.getAnimals())
        {
            newZoo.getAnimals().add(new Animal(a.getAnimaltype()));
        }

        return  zrepos.save(newZoo);
    }

    @Transactional
    @Override
    public Zoo update(Zoo zoo, long id)
    {
        Zoo currentZoo = zrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (zoo.getZooname() != null)
        {
            currentZoo.setZooname(zoo.getZooname());
        }

        if(zoo.getTelephones().size() > 0)
        {
            for (Telephone t : zoo.getTelephones())
            {
                currentZoo.getTelephones().add(new Telephone(t.getPhonetype(), t.getPhonenumber(), currentZoo));
            }
        }

        if(zoo.getAnimals().size() > 0)
        {
            for (Animal a : zoo.getAnimals())
            {
                currentZoo.getAnimals().add(new Animal(a.getAnimaltype()));
            }
        }

        return zrepos.save(currentZoo);
    }
}
