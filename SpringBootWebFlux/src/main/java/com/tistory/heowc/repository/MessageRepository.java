package com.tistory.heowc.repository;

import com.tistory.heowc.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
