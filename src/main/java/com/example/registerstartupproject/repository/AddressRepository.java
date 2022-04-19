package com.example.registerstartupproject.repository;


import com.example.registerstartupproject.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository  extends CrudRepository<Address,Long> {
}
