package com.example.restejbjpa.util;

import com.example.restejbjpa.domain.Company;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class CompanyResponse {
	
	private List<Company> company = new ArrayList<>();

	public List<Company> getCompany() {
		return company;
	}

	public void setCompany(List<Company> company) {
		this.company = company;
	}
}
