package com.dsmpear.main.service.mypage.mypageReport;

import com.dsmpear.main.entity.report.Report;
import com.dsmpear.main.entity.report.ReportRepository;
import com.dsmpear.main.entity.user.UserRepository;
import com.dsmpear.main.entity.userreport.UserReport;
import com.dsmpear.main.entity.userreport.UserReportRepository;
import com.dsmpear.main.exceptions.ReportNotFoundException;
import com.dsmpear.main.payload.response.MyPageReportResponse;
import com.dsmpear.main.payload.response.ProfileReportListResponse;
import com.dsmpear.main.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageReportServiceImpl implements MyPageReportService{

    private final ReportRepository reportRepository;
    private final UserReportRepository userReportRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public ProfileReportListResponse getReport(Pageable page) {
        String email = authenticationFacade.getUserEmail();;

        Page<UserReport> reportPage = userReportRepository.findAllByUserEmail(email, page);

        List<MyPageReportResponse> myPageReportResponses = new ArrayList<>();

        for(UserReport userReport : reportPage){
            Report report = reportRepository.findByReportId(userReport.getReportId())
                    .orElseThrow(ReportNotFoundException::new);

            myPageReportResponses.add(
                    MyPageReportResponse.builder()
                            .reportId(report.getReportId())
                            .title(report.getTitle())
                            .createdAt(report.getCreatedAt())
                            .isSubmitted(report.getIsSubmitted())
                            .isAccepted(report.getIsAccepted())
                            .build()
            );
        }
        return ProfileReportListResponse.builder()
                .totalElements(reportPage.getNumberOfElements())
                .totalPages(reportPage.getTotalPages())
                .myProfileReportResponses(myPageReportResponses)
                .build();
    }

}