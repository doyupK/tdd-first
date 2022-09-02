package com.example.tddfirst.repository;

import com.example.tddfirst.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BoardRepository extends JpaRepository<Board, Integer> {

}
