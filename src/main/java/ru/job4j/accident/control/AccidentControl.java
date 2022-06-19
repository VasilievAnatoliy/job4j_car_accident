package ru.job4j.accident.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.TypeService;

@Controller
@ThreadSafe
public class AccidentControl {
    private final AccidentService accidentService;
    private final TypeService typeService;

    public AccidentControl(AccidentService accidentService, TypeService typeService) {
        this.accidentService = accidentService;
        this.typeService = typeService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", typeService.findAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam("typeId") int id) {
        accident.setType(typeService.findById(id));
        accidentService.addAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/formUpdate")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        model.addAttribute(accidentService.findById(id));
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident,
                         @RequestParam("typeId") int id) {
        accident.setType(typeService.findById(id));
        accidentService.update(accident);
        return "redirect:/";
    }

}
