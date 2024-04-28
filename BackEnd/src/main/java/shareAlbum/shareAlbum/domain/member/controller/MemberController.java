package shareAlbum.shareAlbum.domain.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;

@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/login")
    public String login(@RequestBody String id, @RequestBody String password) {

        //try/catch로 success를 보내주면 된다.
        return " ";
    }


}
