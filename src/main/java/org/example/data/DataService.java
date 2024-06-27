package org.example.data;

import jakarta.annotation.PostConstruct;
import org.example.data.domain.Group;
import org.example.data.domain.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class DataService {

    final Random r = new Random(0);

    final List<Group> groups = Arrays.asList(new Group("Athletes"), new Group("Nerds"), new Group("Collegues"),
            new Group("Students"), new Group("Hunting club members"));

    private List<Person> pagedBase;

    @PostConstruct
    void init() {
        if (pagedBase == null) {
            pagedBase = getListOfPersons(1000);
        }
    }

    public List<Person> findPersons(int start, int pageSize) {
        System.err.println("findAll " + start + " " + pageSize);
        int end = (int) (start + pageSize);
        if (end > pagedBase.size()) {
            end = pagedBase.size();
        }
        return pagedBase.subList((int) start, end);
    }


    public List<Person> getListOfPersons(int total) {
        List<Person> l = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            Person p = new Person();
            p.setId(i + 1);
            p.setFirstName("First" + i);
            p.setLastName("Lastname" + i);
            p.setAge(r.nextInt(100));
            l.add(p);
        }
        return l;
    }

}
