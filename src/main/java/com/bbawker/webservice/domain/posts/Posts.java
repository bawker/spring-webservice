package com.bbawker. webservice.domain.posts;

import com.bbawker.webservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
* Posts 클래스는 실제 DB의 테이블과 매칭될 클래스이며 보통 Entity클래스
* JPA를 사용하시면 DB 데이터에 작업할 경우 실제 쿼리를 날리기 보다는, 이 Entity 클래스의 수정을 통해 작업
*
* @Entity
* 1. 테이블과 링크될 클래스임을 나타냄
* 2. 언더스코어 네이밍(_)으로 이름을 매칭 ex) SalesManager.java -> sales_manager table
*
* @Id   -> 해당 테이블의 PK 필드를 나타냄
*
* @GeneratedValue
* 1. PK의 생성 규칙을 나타냄
* 2. 기본값은 AUTO 로, MySQL의 auto_increment와 같이 자동증가하는 정수형 값이 됨
* 3. 스프링 부트 2.0에선 옵션을 추가하셔야만 auto_increment가 됩니다!
*
* @Column
* 1. 테이블의 컬럼을 나타내면, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됨
* 2. 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있을경우 사용함
* 3. 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex: title),
*   타입을 TEXT로 변경하고 싶거나(ex: content) 등의 경우에 사용됨
*
* Lombok 라이브러리의 어노테이션
*   @NoArgsConstructor
*   1. 기본 생성자 자동 추가
*   2. access = AccessLevel.PROTECTED : 기본생성자의 접근 권한을 protected로 제한
*   3. 생성자로 protected Posts() {}와 같은 효과
*   4. Entity 클래스를 프로젝트 코드상에서 기본생성자로 생성하는 것은 막되,
*       JPA에서 Entity 클래스를 생성하는것은 허용하기 위해 추가
*
*   @Getter   -> 클래스내 모든 필드의 Getter 메소드를 자동생성
*   @Builder
*   1. 해당 클래스의 빌더패턴 클래스를 생성
*   2. 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
**/


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private  String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
