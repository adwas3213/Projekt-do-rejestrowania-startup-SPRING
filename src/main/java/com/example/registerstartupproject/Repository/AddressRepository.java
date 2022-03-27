package com.example.registerstartupproject.Repository;


import com.example.registerstartupproject.Repository.Entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository  extends CrudRepository<Address,Long> {
}
