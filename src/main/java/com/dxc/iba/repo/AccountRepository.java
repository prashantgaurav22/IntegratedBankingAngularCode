package com.dxc.iba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.iba.entity.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>  {

}