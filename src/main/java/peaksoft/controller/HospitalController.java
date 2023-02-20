package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Hospital;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;
    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }
    @GetMapping
    public String getAllCompanies(Model model){
        model.addAttribute("hospitals",hospitalService.getAllHospitals());
        return "hospitals";

    }
    @GetMapping("/new")
    public  String create(Model model) {
        model.addAttribute("newHospital",new Hospital());
        return "savePage";
    }
    @GetMapping("/details/{hospitalId}")
    public String details(@PathVariable("hospitalId") Long hospitalId, Model model){
        model.addAttribute("hospital",hospitalService.getHospitalById(hospitalId));
        return "details";
    }
    @PostMapping("/save")
    public String saveHospital(@ModelAttribute ("newHospital")Hospital hospital){
        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }
    @DeleteMapping("{hospitalId}/delete")
    public String deleteHospital(@PathVariable("hospitalId")Long id){
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }
    @GetMapping("/{hospitalId}/edit")
    public String edit(Model model, @PathVariable("hospitalId")Long id,@ModelAttribute("hospital") Hospital hospital){
        model.addAttribute("hospital",hospitalService.getHospitalById(id));
        return "edit";

    }
    @PutMapping("/{hospitalId}/update")
    public String updateHospital(@PathVariable("hospitalId") Long id,@ModelAttribute("hospital")Hospital hospital){
        hospitalService.updateHospital(id, hospital);
        return "redirect:/hospitals";

    }

    }

