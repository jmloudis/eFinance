package com.ist412.efinance.controller;

import com.ist412.efinance.model.AutoLoan;
import com.ist412.efinance.model.CustomUserDetails;
import com.ist412.efinance.model.Loan;
import com.ist412.efinance.model.User;
import com.ist412.efinance.repository.LoanRepository;
import com.ist412.efinance.repository.UserRepository;
import com.ist412.efinance.service.CustomUserDetailsService;
import com.ist412.efinance.service.LoanServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;
@Slf4j
@Controller
public class LoanController {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private LoanServiceImpl loanServiceImpl;

    @GetMapping("/loans")
    public String getUserLoans(Model model, Principal principal) {
        model.addAttribute("listOfLoans", loanServiceImpl.getAllUserLoans(customUserDetailsService.getUserByPrincipal(principal)));
        return "loans/loans";
    }

    @GetMapping("/newBusinessLoan")
    public String showBusinessLoanApplication(){
        return "loans/business-loan";
    }

    @GetMapping("/newPersonalLoan")
    public String showPersonalLoanApplication(){
        return "loans/personal-loan";
    }

    @GetMapping("/newAutoLoan")
    public String showAutoLoanApplication(Model model){
        Loan autoLoan = new AutoLoan();
        model.addAttribute("autoLoan", autoLoan);
        return "loans/auto-loan";
    }

    @PostMapping("/saveAutoLoan")
    public String saveAutoLoan(@ModelAttribute("autoLoan") AutoLoan autoLoan, Errors errors,
                               Principal principal){
        if(errors.hasErrors()){
            return "loans/auto-loan";
        }
        User applicant = customUserDetailsService.getUserByPrincipal(principal);
        autoLoan.setLoanStatus("SUBMITTED");
        log.info(applicant.toString());
        this.loanServiceImpl.saveLoan(autoLoan, applicant);
        this.loanServiceImpl.getAllLoans();
        return "redirect:/loans";
    }




}
