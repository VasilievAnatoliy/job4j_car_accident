package ru.job4j.accident.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentServiceData;

@Controller
@ThreadSafe
public class IndexControl {
    private final AccidentServiceData accidentService;

    public IndexControl(AccidentServiceData accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
