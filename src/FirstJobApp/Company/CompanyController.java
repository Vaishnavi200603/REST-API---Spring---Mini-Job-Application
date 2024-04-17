package com.example.firstJobApp.Company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.addCompany(company);
        return new ResponseEntity<>("Company Added Successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyInfoById(@PathVariable Long id){
        Company company = companyService.getCompanyInfoById(id);
        if(company != null) return new ResponseEntity<>(company, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        boolean deleted = companyService.deleteCompanyById(id);
        if(deleted) return new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK);
        return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyInfo(@PathVariable Long id,
                                                    @RequestBody Company updatedCompany) {
        boolean updated = companyService.updateCompanyInfo(id, updatedCompany);
        if(updated) return new ResponseEntity<>("Company Info Successfully Updated", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
