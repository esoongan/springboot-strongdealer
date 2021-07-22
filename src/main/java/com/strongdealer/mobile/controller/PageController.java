package com.strongdealer.mobile.controller;


import com.strongdealer.mobile.dto.paging.PagingResponseDto;
import com.strongdealer.mobile.model.ApiResponse;
import com.strongdealer.mobile.model.HttpResponseMessage;
import com.strongdealer.mobile.model.HttpStatusCode;
import com.strongdealer.mobile.service.PagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

// 페이지별로 게시글목록을 조회
@CrossOrigin
@RequiredArgsConstructor
@RestController
public class PageController {

    private final PagingService pagingService;

//    //컨트롤러메소드에 pageble타입의 파라미터가 존재하면 요청파라미터를 토대로 PageableHandlerMethodArgumentResolver가 pageRequest를 생성함
//
//    // 경매중인 차 페이지 조회
//    @GetMapping("/api/post/page")
//    public ResponseEntity<Page<PagingResponseDto>> getPaging(@PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageRequest,
//                                                             @RequestParam(required = false) String manufacture,
//                                                             @RequestParam(required = false) String model,
//                                                             @RequestParam(required = false) String rating,
//                                                             @RequestParam(required = false) String price) {
//
//
//        Page<PagingResponseDto> pagingDtos = pagingService.searchPaging(manufacture, model, rating, price, pageRequest);
//        return new ResponseEntity(ApiResponse.response(
//                HttpStatusCode.OK,
//                HttpResponseMessage.GET_SUCCESS,
//                pagingDtos), HttpStatus.OK);
//    }
//
//    // 토큰 소유자가 작성한 게시글페이징 조회
//    @GetMapping("/uauth/api/post")
//    public ResponseEntity<Page<PagingResponseDto>> pagingByWriter(@PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageRequest,
//                                                                  Authentication authentication) {
//        Page<PagingResponseDto> pagingDtos = pagingService.pagingByWriter(pageRequest, authentication);
//        return new ResponseEntity(ApiResponse.response(
//                HttpStatusCode.OK,
//                "토큰소유자가 작성한 "+ HttpResponseMessage.GET_SUCCESS,
//                pagingDtos), HttpStatus.OK);
//
//    }


}
