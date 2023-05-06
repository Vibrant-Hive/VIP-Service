package com.vip.coders.repository;

import com.vip.coders.entity.SupportRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRequestRepository extends CrudRepository<SupportRequest, Integer> {
    SupportRequest findByLearnerIdAndMentorId(int learnerId, int mentorId);
}

