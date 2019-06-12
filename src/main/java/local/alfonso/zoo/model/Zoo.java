package local.alfonso.zoo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zoo")
public class Zoo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long zooid;
    private String zooname;

    @OneToMany(mappedBy = "zoo")
    private List<Telephone> telephones = new ArrayList<>();

    public Zoo() {
    }

    public Zoo(String zooname) {
        this.zooname = zooname;
    }

    public long getZooid() {
        return zooid;
    }

    public void setZooid(long zooid) {
        this.zooid = zooid;
    }

    public String getZooname() {
        return zooname;
    }

    public void setZooname(String zooname) {
        this.zooname = zooname;
    }

    public List<?> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }
}
