package com.dxc.iba.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.iba.entity.Beneficiary;


public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Integer> {

}
