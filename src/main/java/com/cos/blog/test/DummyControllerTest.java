package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController //데이터를 return하는 controller
public class DummyControllerTest {

    // dummy/join에 요청이 들어오게 되면 http의 body에 username, password, email 데이터를 가지고 요청
    /*@PostMapping("/dummy/join")
    public String join(String username, String password, String email){
        // 변수명을 정확하게 적으면 @requestparam 사용 안 해도 됨
        // xxx-form 방식: key=value > spring이 파싱해서 넣어줌

        System.out.println("username: " + username +", password: " + password + ", email: "+ email);
        return "회원가입이 완료되었습니다.";
    }*/

    @Autowired // 의존성 주입 DIO
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(User user){
        user.setRole(RoleType.USER);
        userRepository.save(user);

        System.out.println("id: " + user.getId());
        System.out.println("username: " + user.getUsername() +", password: " + user.getPassword() + ", email: "+ user.getEmail());
        System.out.println("role: " + user.getRole());
        System.out.println("createDate: " + user.getCreateDate());

        return "회원가입이 완료되었습니다.";
    }

    // {id} 주소로 파라미터 전달을 받을 수 있음
    // /dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        /*User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
            @Override
            public User get() {
                return new User();
            }
        });*/
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + " 유저는 없습니다."));

        // user 오브젝트를 웹 브라우저가 이해할 수 있는 데이터로 변환(JSON)
        // Spring Boot는 messageconverter가 응답 시 자동 작동해서 jackson 라이브러리를 호출해 자바 오브젝트를 JSON으로 변환해 던짐
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    //한 페이지당 2건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public  List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<User> PagingUser = userRepository.findAll(pageable);

        List<User> users = PagingUser.getContent();
        return users;
    }

    // email, password
    @Transactional //save를 사용하지 않고도 update, 함수 종료 시 자동 commit
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        // @RequestBody 이용, JSON 데이터 요청을 JavaObject로 변환해서 받아준다. (MessageConverter의 jackson 라이브러리)

        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정 실패");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // userRepository.save(user);
        // save함수는 id를 전달하지 않으면 insert
        // id를 전달했을 때 id 데이터가 있으면 update, 없으면 insert

        // 더티 체킹...
        return user;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패했습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다. id: " + id;
    }
}
