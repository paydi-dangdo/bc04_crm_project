package com.cybersoft.crm04.controller;

import com.cybersoft.crm04.entity.RolesEntity;
import com.cybersoft.crm04.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.Role;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RolesRepository rolesRepository;

    @GetMapping("/add")
    public String addRole() {

        return "role-add";
    }

    @PostMapping("/add")
    public String processAddRole(@RequestParam String roleName, @RequestParam String desc, Model model) {

        boolean result;

        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setName(roleName.toUpperCase());
        rolesEntity.setDescription(desc);

        try {
            rolesRepository.save(rolesEntity);
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = false;
        }

        model.addAttribute("result", result);
        return "role-add";
    }
}
