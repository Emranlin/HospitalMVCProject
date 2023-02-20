package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Department;
import peaksoft.service.DepartmentService;

@Controller
@RequestMapping("/{id}/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping
    public String getAll(@PathVariable("id") Long id, Model model){
        model.addAttribute("departments", departmentService.getAllDepartment(id));
        return "department/departments";
    }
    @GetMapping("/new")
    public String create( Model model){
        model.addAttribute("newDepartment", new Department());
        return "department/savePage";
    }
    @PostMapping("/save")
    public String save(@PathVariable("id") Long id,
                       @ModelAttribute("department") Department department){
        departmentService.save(id, department);
        return "redirect:/{id}/departments";
    }
    @GetMapping("/{departmentId}/edit")
    public String getUpdateForm(@PathVariable("departmentId") Long departmentId,
                                Model model){
        model.addAttribute("olDepartment", departmentService.getDepartmentById(departmentId));
        return "department/edit";
    }
    @PatchMapping("/{departmentId}/update")
    public String update(@PathVariable("departmentId") Long departmentId,
                         @ModelAttribute("department") Department department){
        departmentService.updateDepartment(departmentId, department);
        return "redirect:/{id}/departments";
    }
    @DeleteMapping("{departmentId}/delete")
    public String delete(@PathVariable("departmentId") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return "redirect:/{id}/departments";
    }
}