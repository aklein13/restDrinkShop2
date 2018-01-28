package com.example.restejbjpa.util;

import com.example.restejbjpa.domain.Buyer;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class BuyerResponse {
	
	private List<Buyer> buyer = new ArrayList<>();

	public List<Buyer> getBuyer() {
		return buyer;
	}

	public void setBuyer(List<Buyer> buyer) {
		this.buyer = buyer;
	}
}
