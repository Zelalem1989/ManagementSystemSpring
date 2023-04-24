package com.authorzelalem.managementsystemmapper.controller;


import com.authorzelalem.managementsystemmapper.model.Material;
import com.authorzelalem.managementsystemmapper.service.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RequestMapping("/material")
@Slf4j
@Controller
@AllArgsConstructor
public class MaterialController {


    private static final String CREATE_OR_UPDATE_FORM = "material/createOrUpdateMaterialForm";
    private final CustomerService customerService;
    private final OrderItemService orderItemService;
    private final OrderService orderService;
    private final ProductService productService;

    private final MaterialService materialService;


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/{id}/edit")
    public String initUpdateProductForm(@PathVariable Long id, Model model ) {
        model.addAttribute(materialService.findById(id));
        return CREATE_OR_UPDATE_FORM;
    }
    @PostMapping("/{id}/edit")
    public String processUpdateMaterialForm(@Valid Material material, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_FORM;
        } else {
            material.setId(id);
            Material savedMaterial= materialService.save(material);
            return "redirect:/material/" + savedMaterial.getId();
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteMaterialById(@PathVariable Long id){

        //log.debug("Deleting id: " + id);
        materialService.deleteById(id);

        return "redirect:/material/index";
    }

    @GetMapping("/list")
    public String listMaterial(Model model) {

        Set<Material> materialList = materialService.findAll();
        System.out.println("Material List " + materialList.toString());

        if (materialList.isEmpty()) {
            return "customer/notFound";

        } else {
            model.addAttribute("materialList", materialList);
            return "material/materialList";
        }
    }

    @GetMapping
    @RequestMapping("/new")
    public String initCreationForm(Model model) {

        model.addAttribute("material", new Material());

        return CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Material material, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_FORM;
        } else {
            Material savedMaterial =  materialService.save(material);
            return "redirect:/material/" + savedMaterial.getId();

        }
    }
    @GetMapping
    @RequestMapping("/{id}")
    public String showMaterial(@PathVariable Long id, Model model) {

        Material results = materialService.findById(id);

        model.addAttribute("material", results);

        return "/material/materialDetails";

    }
}
