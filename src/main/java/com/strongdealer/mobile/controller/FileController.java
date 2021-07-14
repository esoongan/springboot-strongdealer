package com.strongdealer.mobile.controller;

import com.strongdealer.mobile.dto.File.FileResponseDto;
import com.strongdealer.mobile.model.ApiResponse;
import com.strongdealer.mobile.model.HttpResponseMessage;
import com.strongdealer.mobile.model.HttpStatusCode;
import com.strongdealer.mobile.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    // 이미지 업로드
    // 요청에서 파일객체를 받아 DB에 저장하고 결과 반환
    @PostMapping("/api/file/{carId}")
    public List<FileResponseDto> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files, @PathVariable Long carId) {
        List<FileResponseDto> fileDtoList = fileService.uploadFiles(files, carId);
        return fileDtoList;
    }

    // 게시글PK에 연관된 전체 파일조회
    // FileDto로 반환한다. -> 밑에거에서 id값은 빠짐
    @GetMapping("/api/file/{carId}")
    public List<FileResponseDto> getFiles(@PathVariable Long postId) {
        return fileService.getFile(postId);
    }

    @DeleteMapping("/api/file/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long id) throws FileNotFoundException {
        fileService.deleteFile(id);
        return new ResponseEntity(ApiResponse.response(
                HttpStatusCode.OK,
                HttpResponseMessage.DELETE_SUCCESS,
                id), HttpStatus.OK
        );
    }

}