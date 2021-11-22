package com.ist412.efinance.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Loan implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @Column(name = "loan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;

    @Column(name = "ssn", length = 45)
    private int socialSecurityNumber;

    @Column(name = "employer", length = 45)
    private String employer;

    @Column(name = "job_title", length = 45)
    private String jobTitle;

    @Column(name = "annual_salary", length = 15)
    private int annualSalaryAmount;

    @Column(name = "intreset_rate", length = 10)
    private Double interestRate;

    @Column(name = "term_in_months", length = 4)
    private int loanTermInMonths;

    @Column(name = "loan_amount", length = 25)
    private Double loanAmount;

    //@ManyToMany(fetch = FetchType.LAZY)
    //@JoinColumn(name = "applicatn_id")
    //private User applicant;


}
