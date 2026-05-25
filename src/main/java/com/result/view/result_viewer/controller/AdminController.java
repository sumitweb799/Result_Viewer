package com.result.view.result_viewer.controller;

import com.result.view.result_viewer.dto.StudentForm;
import com.result.view.result_viewer.entity.Mark;
import com.result.view.result_viewer.entity.Student;
import com.result.view.result_viewer.repository.StudentRepo;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private StudentRepo studentRepo;
    private ModelMapper modelMapper;

    public AdminController(StudentRepo studentRepo, ModelMapper modelMapper) {
        this.studentRepo = studentRepo;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/result_page")
    public String redirectHandler(){
        return "redirect:/admin/add_result";
    }


    @GetMapping("/add_result")
    public String addResultForm(Principal principal, Model model){

        String name = principal.getName();
        System.out.println(name);

        StudentForm studentForm = new StudentForm();

        List<String> standardOptions = new ArrayList<>();
        standardOptions.add("CLASS 1");
        standardOptions.add("CLASS 2");
        standardOptions.add("CLASS 3");

        model.addAttribute("studentForm", studentForm);
        model.addAttribute("standardOptions", standardOptions);
        model.addAttribute("name", name);
        return "admin/add_result";
    }

    @RequestMapping(value = "/add_result_action", method = RequestMethod.POST)
    public String processAddResultForm(@Valid @ModelAttribute StudentForm studentForm, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            List<String> standardOptions = new ArrayList<>();
            standardOptions.add("CLASS 1");
            standardOptions.add("CLASS 2");
            standardOptions.add("CLASS 3");

            model.addAttribute("standardOptions", standardOptions);
            return "admin/add_result";
        }

        //convert student form to student entity

        Student student = modelMapper.map(studentForm, Student.class);
        student.setId(UUID.randomUUID().toString());

        //her marks to attach student

        List<Mark> updateList = student.getMarks().stream().map(mark -> {
            mark.setStudent(student);
            return mark;
        }).toList();


        //update student List

        student.setMarks(updateList);


        Student savedStudent = studentRepo.save(student);
        return "redirect:/admin/add_result?message=Student added successfully";
    }

}
