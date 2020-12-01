package com.dsmpear.main.service.notice;

import com.dsmpear.main.entity.notice.Notice;
import com.dsmpear.main.entity.notice.NoticeRepository;
import com.dsmpear.main.exceptions.NoticeNotFoundException;
import com.dsmpear.main.payload.response.NoticeContentResponse;
import com.dsmpear.main.payload.response.NoticeListResponse;
import com.dsmpear.main.payload.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    @Override
    public NoticeListResponse getNoticeList(Pageable page) {
        //size는 어떻게 써야하는가
        Page<Notice> noticePage = noticeRepository.findAllBy(page);

        List<NoticeResponse> noticeResponses = new ArrayList<>();

        for(Notice notice : noticePage){
            noticeResponses.add(
                    NoticeResponse.builder()
                            .title(notice.getTitle())
                            .createdAt(notice.getCreatedAt())
                            .build()
            );
        }

        return NoticeListResponse.builder()
                .totalElements((int) noticePage.getTotalElements())
                .totalPages(noticePage.getTotalPages())
                .noticeResponses(noticeResponses)
                .build();
    }

    @Override
    public NoticeContentResponse getNoticeContent(Integer noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(NoticeNotFoundException::new);

        return NoticeContentResponse.builder()
                .title(notice.getTitle())
                .description(notice.getDescription())
                .fileName(notice.getFileName())
                .createdAt(notice.getCreatedAt())
                .build();
    }

}
