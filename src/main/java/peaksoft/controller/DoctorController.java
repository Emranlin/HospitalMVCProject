package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Doctor;
import peaksoft.service.DoctorService;

@Controller
@RequestMapping("/{id}/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @GetMapping()
    public String getAll(@PathVariable("id") Long id, Model model){
        model.addAttribute("doctors", doctorService.getAllDoctors(id));
        return "doctor/doctors";
    }

    @GetMapping("/new")
    public String createNewDoctor( Model model){
        model.addAttribute("newDoctor", new Doctor());
        return "doctor/doctors";
    }
    @PostMapping("/save")
    public String save(
                       @ModelAttribute("newDoctor") Doctor doctor){
        doctorService.save( doctor);
        return "redirect:/{id}/doctors";


    }
    @GetMapping("/{doctorId}/edit")
    public String getUpdateFrom(@PathVariable("doctorId") Long doctorId,
                                Model model){
        model.addAttribute("oldDoctor", doctorService.getDoctorById(doctorId));
        return "doctor/edit";
    }
    @PatchMapping("/{doctorId}/update")
    public String update(@PathVariable("doctorId") Long doctorId,
                         @ModelAttribute("doctor") Doctor doctor){
        doctorService.updateDoctor(doctorId, doctor);
        return "redirect:/{id}/doctors";
    }
    @DeleteMapping("/{doctorId}/delete")
    public String delete(@PathVariable("doctorId") Long doctorId){
        doctorService.deleteDoctor(doctorId);
        return "redirect:/{id}/doctors";
    }
}