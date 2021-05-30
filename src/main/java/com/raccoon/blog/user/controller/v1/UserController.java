import com.raccoon.blog.global.config.jwt.JwtTokenProvider;
import com.raccoon.blog.user.domain.Member;
import com.raccoon.blog.user.dto.ReqDto;
import com.raccoon.blog.user.dto.SignInDto;
import com.raccoon.blog.user.dto.SignUpDto;
import com.raccoon.blog.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/user/*")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder pwEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwttokenprovider;

    @GetMapping("admin/users")
    public Iterable<Member> allUsers() {
        return memberRepository.findAll();
    }

    @PostMapping("signup")
    public ResponseEntity<Member> join (@RequestBody SignUpDto signDto){

        String encryptPw = pwEncoder.encode(signDto.getPassword());

        Member member =Member.builder()
                .uid(signDto.getId())
                .uname(signDto.getName())
                .password(encryptPw)
                .roles(signDto.getRoles())
                .build();

        memberRepository.save(member);

        return new ResponseEntity<>(member,HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<ReqDto> signin (@RequestBody SignInDto signDto){

        Member user = memberRepository.findById(signDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("no such data"));

        UsernamePasswordAuthenticationToken authenticator = new UsernamePasswordAuthenticationToken(signDto.getId(), signDto.getPassword());
        authenticationManager.authenticate(authenticator);

        if (!pwEncoder.matches(signDto.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("no such data");

        List<String> list = new ArrayList<String>();
        user.getRoles().forEach(role -> list.add(role.getRoleName()));
        String token =jwttokenprovider.createToken(user.getUid(), list);

        ReqDto req = new ReqDto(user.getUname(),token);

        return new ResponseEntity<>(req,HttpStatus.CREATED);
    }
}