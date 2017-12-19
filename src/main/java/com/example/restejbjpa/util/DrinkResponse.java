package com.example.restejbjpa.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.restejbjpa.domain.Drink;

@XmlRootElement
public class DrinkResponse {
	
	private List<Drink> drink = new ArrayList<>();

	public List<Drink> getDrink() {
		return drink;
	}

	public void setDrink(List<Drink> drink) {
		this.drink = drink;
	}
}
