package com.strongdealer.mobile.domain.File;

import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class File {

    // 파일번호 - PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 여러개의 파일이 하나의 차에 매핑
    @ManyToOne(targetEntity = Car.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false) // 매핑할 외래키 이름지정 - car엔티티의 id필드를 외래키로 갖겠다.
    private Car car;

    // 원본파일명
    @Column(nullable = false) // null값 허용x
    private String origFilename;

    // 서버에 저장될 파일명
    @Column(nullable = false) // null값 허용x
    private String filename;

    @Column(nullable = false)
    private String filepath;

    // 전달받은 requestDto를 이용해 빌더로 객체생성하고 생성한 객체를 JPA에게 넘겨 디비에 저장
    @Builder
    public File(Car car, String origFilename, String filename, String filepath) {
        this.car = car;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filepath = filepath;
    }
}
