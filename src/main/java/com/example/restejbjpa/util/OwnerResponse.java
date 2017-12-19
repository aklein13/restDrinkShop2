package com.example.restejbjpa.util;

import com.example.restejbjpa.domain.Owner;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class OwnerResponse {
	
	private List<Owner> owner = new ArrayList<>();

	public List<Owner> getOwner() {
		return owner;
	}

	public void setOwner(List<Owner> owner) {
		this.owner = owner;
	}
}
