package com.example.tddfirst.repository;

import com.example.tddfirst.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

}
