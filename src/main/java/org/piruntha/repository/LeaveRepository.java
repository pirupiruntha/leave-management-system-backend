package org.piruntha.repository;

import org.piruntha.model.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LeaveRepository extends MongoRepository<Leave, String> {
    List<Leave> findAllByEmpUsername(String username);

}
