package com.result.view.result_viewer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.result.view.result_viewer.entity.Mark;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentForm {

    @NotBlank(message = "Name is required !!")
    private  String name;
    @NotBlank(message = "Roll No. is required !!")
    private String rollNumber;
    @NotBlank(message = "Invalid Email !!")
    private String email;
    @NotBlank(message = "Address is required !! ")
    private String address;
    @NotBlank(message = "School is required !! ")
    private String schoolName;
    private String photoName;
  //  @NotBlank(message = "DOB is required !! ")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @NotBlank(message = "standard is required !! ")
    private String standard;
    @NotBlank(message = "FatherName is required !! ")
    private String fatherName;
    @NotBlank(message = "Gender is required !! ")
    private String gender;

    private List<MarksForm> marks = new ArrayList<>();
}
