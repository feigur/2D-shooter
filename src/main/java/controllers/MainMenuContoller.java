package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.AccountService;
import storage.Entities.Account;

import javax.persistence.Id;
import java.util.List;

@Controller
public class MainMenuContoller {
    private AccountService accountService;

    @Autowired
    public MainMenuContoller(AccountService accountService){
        this.accountService = accountService;
    }

    @RequestMapping("/")
    public String homePage(Model model){
        //Call a method in a Service Class
        //Add some data to the Model
        return "startup";
    }

    @RequestMapping(value = "/CreateAccount", method = RequestMethod.POST)
    public String createAccount(Account account, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/MainMenu";
        }
        accountService.save(account);
        return "redirect:/MainMenu";
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String createAccount(String accountName, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/MainMenu";
        }
        accountService.get(accountName);
        return "redirect:/MainMenu";
    }


}
