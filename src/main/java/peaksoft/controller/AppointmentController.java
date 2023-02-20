package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Appointment;
import peaksoft.service.AppointmentService;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.PatientService;


@Controller
@RequestMapping("/{id}/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final DepartmentService departmentService;

    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService, DepartmentService departmentService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.departmentService = departmentService;
    }
    @GetMapping
    public String getAll(@PathVariable("id")Long id, Model model){
        model.addAttribute("appointments", appointmentService.getAllAppointments(id));
        return "redirect:/{id}/appointments";
    }
    @GetMapping("/new")
    public String create(@PathVariable("id")Long id, Model model){
        model.addAttribute("newAppointment", new Appointment());
        model.addAttribute("doctors", doctorService.getAllDoctors(id));
        model.addAttribute("patients", patientService.getAllPatients(id));
        model.addAttribute("departments", departmentService.getAllDepartment(id));
        return "appointment/savePage";
    }
    @PostMapping("/save")
    public String save(@PathVariable("id") Long id,
                       @ModelAttribute("appointment") Appointment appointment){
        appointmentService.save(id, appointment);
        return "redirect:/{id}/appointments";
    }
    @GetMapping("/{appointmentId}/edit")
    public String getUpdate(@PathVariable("appointmentId") Long appointmentId,
                                Model model){
        model.addAttribute("oldAppointment", appointmentService.getAppointmentById(appointmentId));
        return "appointment/edit";
    }
    @PatchMapping("/{appointmentId}/update")
    public String update(@PathVariable("id") Long id,
                         @PathVariable("appointmentId") Long appointmentId,
                         @ModelAttribute("appointment") Appointment appointment){
        return "redirect:/{id}/appointments";
    }
}