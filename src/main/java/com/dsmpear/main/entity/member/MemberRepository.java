package com.dsmpear.main.entity.member;

import com.dsmpear.main.entity.report.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member,Integer> {
    Page<Member> findAllByReport(Report report, Pageable page);
    Optional<Member> findByReportAndUserEmail(Report report, String userEmail);
    void deleteAllByReport(Report report);
}
