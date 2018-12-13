package org.academiadecodigo.invictus.controller;

import org.academiadecodigo.invictus.converter.DtoToUser;
import org.academiadecodigo.invictus.converter.UserToDto;
import org.academiadecodigo.invictus.dto.UserDto;
import org.academiadecodigo.invictus.model.User;
import org.academiadecodigo.invictus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;
    private DtoToUser dtoToUser;
    private UserToDto usertoDto;


    @Autowired
    public void setDtoToUser(DtoToUser dtoToUser) {
        this.dtoToUser = dtoToUser;
    }

   @Autowired
    public void setUserToDto(UserToDto usertoDto) {
        this.usertoDto = usertoDto;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path ="")
    public String home(){

        return "index";
    }

    @GetMapping(path ="/{id}")
    public String showUser(@PathVariable Integer id, Model model){

        User user = userService.get(id);

        model.addAttribute("user", usertoDto.convert(user));

        return "user";

    }




    @GetMapping(path = "/{id}/edit")
    public String editUser(@PathVariable Integer id, Model model){
        model.addAttribute("user",usertoDto.convert(userService.get(id)));

        return "add-update";

    }

    @GetMapping(path = "/user/form")
    public String form (Model model){
        model.addAttribute("user",new UserDto());

        return "form";
    }




    @PostMapping(path = "/form")
    public String submit(@ModelAttribute("user") UserDto userDto){

        return"redirect:/user";

    }



}
