package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentServiceData {
    private final AccidentRepository accidents;

    public AccidentServiceData(AccidentRepository accidents) {
        this.accidents = accidents;
    }

    @Transactional
    public Accident addAccident(Accident accident) {
        return accidents.save(accident);
    }

    @Transactional
    public List<Accident> findAll() {
        List<Accident> res = new ArrayList<>();
        accidents.findAll().forEach(res::add);
        return res;
    }

    @Transactional
    public Accident findById(int id) {
        return accidents.findById(id).get();
    }
}
