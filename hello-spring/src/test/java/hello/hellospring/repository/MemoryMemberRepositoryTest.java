package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * 회원 리포지토리 메모리 구현체 테스트
 *
 *
 * 이해안되는 부분:
 * @AfterEach 가 무엇인지
 * => 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 이렇게 되면
 * 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다 @AfterEach를 사용하면 각 테스트가 종료될 때마다
 * 이 기능을 실행한다. 여기서는 메모리 DB에 저장된 데이터를 삭제한다.
 * => 테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.
 *
 * assertThat 이 무엇인지
 * isEqualTo 이 무엇인지
 * 각 클래스들이 어떤 기능을 테스트햇는지
 *
 *
 * 자바는 JUnit 이라는 프레임워크로 테스트를 실행한다
 */

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();

        assertThat(result).isEqualTo(member);

    }

    @Test
    public void findByName(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(member1);



    }

    @Test
    public void findAll(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);

    }


}
