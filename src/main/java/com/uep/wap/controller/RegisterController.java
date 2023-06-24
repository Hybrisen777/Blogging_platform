package com.uep.wap.controller;

import com.uep.wap.model.Account;
import com.uep.wap.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "register";
    }
    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Account account, RedirectAttributes redirectAttributes) {

        if (account.getUsername().isEmpty() || account.getPassword().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "User's name and password cannot be empty.");
            return "redirect:/register?error";
        }

        //sprawdzanie czy użytkownik o podanej nazwie użytkownika już istnieje
        if (accountService.findByUsername(account.getUsername()).isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "This username is already taken");
            return "redirect:/register?error";
        }

        accountService.save(account);

        return "redirect:/";
    }
}
