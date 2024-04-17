package com.example.firstJobApp.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    void addCompany(Company company);

    Company getCompanyInfoById(Long Id);
    boolean deleteCompanyById(Long Id);
    boolean updateCompanyInfo(Long Id, Company updatedCompany);
}
