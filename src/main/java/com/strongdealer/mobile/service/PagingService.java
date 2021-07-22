package com.strongdealer.mobile.service;

import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import com.strongdealer.mobile.domain.Car.CarInfo.CarRepository;
import com.strongdealer.mobile.domain.Car.CarInfo.CarSpecification;
import com.strongdealer.mobile.domain.File.FileRepository;
import com.strongdealer.mobile.domain.User.User;
import com.strongdealer.mobile.dto.File.FileResponseDto;
import com.strongdealer.mobile.dto.paging.PagingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PagingService {

    private final CarRepository carRepository;
    private final FileRepository fileRepository;
    private final FileService fileService;
    private final UserService userService;


//    @Transactional
//    public Page<PagingResponseDto> searchPaging(String manufacture, String model, String rating, String price, Pageable pageable) {
//        Specification<Car> spec = null;
//
//        if(spec == null) {
//            return paging(pageable);
//        }
//
//        Page<Car> carPage = carRepository.findAll(spec, pageable);
//        return carPage.map(
//                car -> new PagingResponseDto(
//                        car.getId(),
//                        fileService.getFile(car.getId()),
//
//                ));
//
//    }
//
//    @Transactional(readOnly = true)
//    // default : 페이징으로 게시글 반환
//    public Page<PagingResponseDto> paging(Pageable pageable){
//
//        Page<Car> carList = carRepository.findAll(pageable);
//
//        // carList 각각의 car들을 하나씩 dto로 바꿔서 pagingList에 담아서 이걸 리턴
//        Page<PagingResponseDto> pagingDtos = carList.map(
//                car -> new PagingResponseDto(
//                        car.getId(),
//                        // 제일 첫번째 사진만 하나 가져온다.
//                        fileService.getFile(car.getId()),
//                        car.getCarNo(),
//                        car.getUser().getUsername(),
//                        car.getCategory(),
//                        car.getUser().getAddr(),
//                        car.getCreatedDate()
//                ));
//        return pagingDtos;
//    }
//
//    // 내가쓴글 조회
//    @Transactional(readOnly = true)
//    public Page<PagingResponseDto> pagingByWriter(Pageable pageable, Authentication authentication){
//
//        Specification<Car> spec = CarSpecification.equalWriter(userService.getUserByToken(authentication.getPrincipal()));
//
//        Page<Post> postPage = postsRepository.findAll(spec, pageable);
//
//        return postPage.map(
//                post -> new PagingResponseDto(
//                        post.getId(),
//                        new FileResponseDto(fileRepository.findAllByPostId(post.getId()).get(0)),
//                        post.getTitle(),
//                        post.getUser().getUsername(),
//                        post.getCategory(),
//                        post.getUser().getAddr(),
//                        post.getCreatedDate()
//                ));
//    }

}
