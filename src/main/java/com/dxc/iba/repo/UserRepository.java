package com.dxc.iba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.iba.entity.BankUser;


@Repository
public interface UserRepository  extends JpaRepository<BankUser,String>{

}
