package ru.job4j.accident.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@ThreadSafe
public class AccidentControl {
    private final AccidentServiceData accidentService;
    private final TypeServiceData typeService;
    private final RuleServiceData ruleService;

    public AccidentControl(AccidentServiceData accidentService,
                           TypeServiceData typeService, RuleServiceData ruleService) {
        this.accidentService = accidentService;
        this.typeService = typeService;
        this.ruleService = ruleService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rules", ruleService.findAll());
        model.addAttribute("types", typeService.findAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam("typeId") int id,
                       HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Arrays.stream(ids).forEach(i -> accident.addRule(
                ruleService.findById(Integer.parseInt(i))));
        accident.setType(typeService.findById(id));
        accidentService.addAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/formUpdate")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        model.addAttribute("rules", ruleService.findAll());
        model.addAttribute(accidentService.findById(id));
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident,
                         @RequestParam("typeId") int id,
                         HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Arrays.stream(ids).forEach(i -> accident.addRule(
                ruleService.findById(Integer.parseInt(i))));
        accident.setType(typeService.findById(id));
        accidentService.addAccident(accident);
        return "redirect:/";
    }
}
