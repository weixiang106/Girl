package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StuRepository extends JpaRepository<Stu,Integer> {
//   public List<Stu> findByStuId(Long id);
}
