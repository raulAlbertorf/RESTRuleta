package com.ibm.academia.apirest.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.models.entities.Ruleta;

@Repository
public interface IRuletaRepository extends PagingAndSortingRepository<Ruleta, Long> {

}
