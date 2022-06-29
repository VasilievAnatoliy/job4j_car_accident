package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.model.Type;
import ru.job4j.accident.repository.RuleRepository;
import ru.job4j.accident.repository.TypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceData {
    private final TypeRepository types;

    public TypeServiceData(TypeRepository types) {
        this.types = types;
    }

    public List<Type> findAll() {
        List<Type> res = new ArrayList<>();
        types.findAll().forEach(res::add);
        return res;
    }

    public Type findById(int id) {
        return types.findById(id).get();
    }
}
