package com.authorzelalem.managementsystemmapper.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

//@Value
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NonNull
    private Long id;
    @NotBlank
    private String Name;
    private String email;
    private String phoneNumber;
    private Long tinNumber;
    private Set<OrderDto> orders  = new HashSet<>();
}
