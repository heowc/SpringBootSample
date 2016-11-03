package com.example.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

	@Query("SELECT DISTINCT si FROM office_del")
	List<String> findBySi();
	
	@Query("SELECT DISTINCT gu FROM office_del WHERE si = ?")
	List<String> findByGu(String si);
	
	@Query("SELECT DISTINCT dong FROM office_del WHERE si = ? AND gu = ?")
	List<String> findByDong(String si, String gu);
	
	@Query("SELECT DISTINCT ri FROM office_del WHERE si = ? AND gu = ? AND dong = ?")
	List<String> findByRi(String si,String gu, String dong);
	
	List<Delivery> findBySiAndGuAndDongAndRiContaining(String si,String gu, String dong, String ri);
	
	@Query("SELECT d FROM office_del d WHERE d.dong like %?1% OR d.ri LIKE %?1%")
	List<Delivery> findByDongContainingOrRiContaining(String keyword);
}
