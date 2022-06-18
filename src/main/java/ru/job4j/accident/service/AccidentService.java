package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
@ThreadSafe
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accident) {
        this.accidentMem = accident;
    }

    public Accident addAccident(Accident accident) {
        return accidentMem.addAccident(accident);
    }

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }
}
