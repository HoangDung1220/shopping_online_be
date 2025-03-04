package com.shoppingonline.example;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleModel, Long>{

}
