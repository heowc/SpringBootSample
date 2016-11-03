package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Delivery;
import com.example.domain.DeliveryRepository;

@RestController
public class DeliveryController {

	@Autowired
	DeliveryRepository repository;
	
	@PostMapping(value = "/findAddr",
				 produces = "application/json")
	public List<String> findAddr(@RequestBody(required=false) Delivery delivery){
		if(delivery != null){
			if(delivery.getDong() != null && !"".equals(delivery.getDong())){
				return repository.findByRi(delivery.getSi(), delivery.getGu(), delivery.getDong());
			}
			if(delivery.getGu() != null && !"".equals(delivery.getGu())){
				return repository.findByDong(delivery.getSi(), delivery.getGu());
			}
			if(delivery.getSi() != null && !"".equals(delivery.getSi())){
				return repository.findByGu(delivery.getSi());
			}
		}
		return repository.findBySi();
	}
	
	@GetMapping(value = "/findKeyword")
	public List<Delivery> findKeyword(@RequestParam(value="keyword") String keyword){
		return repository.findByDongContainingOrRiContaining(keyword);
	}
	
	@PostMapping(value = "/findDetailAddr",
				 produces = "application/json")
	public List<Delivery> findDetailAddr(@RequestBody Delivery delivery){
		return repository.findBySiAndGuAndDongAndRiContaining(delivery.getSi(), delivery.getGu(), delivery.getDong(), delivery.getRi());
	}
}
