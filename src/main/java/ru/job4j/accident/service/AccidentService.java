package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;


import java.util.List;

/*@Service*/
@ThreadSafe
public class AccidentService {
    private final AccidentHibernate accidents;

    public AccidentService(AccidentHibernate accidents) {
        this.accidents = accidents;
    }

    public Accident addAccident(Accident accident) {
        return accidents.save(accident);
    }

    public List<Accident> findAll() {
        return accidents.getAll();
    }

    public Accident findById(int id) {
        return accidents.findById(id);
    }

    public void update(Accident accident) {
        accidents.update(accident);
    }
}
