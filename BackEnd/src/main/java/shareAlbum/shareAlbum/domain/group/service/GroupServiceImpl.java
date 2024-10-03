package shareAlbum.shareAlbum.domain.group.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shareAlbum.shareAlbum.domain.group.dto.GroupCreateDto;
import shareAlbum.shareAlbum.domain.group.dto.GroupInvitationDto;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;
import shareAlbum.shareAlbum.domain.group.entity.Invitation;
import shareAlbum.shareAlbum.domain.group.entity.InvitationStatus;
import shareAlbum.shareAlbum.domain.group.entity.MyGroup;
import shareAlbum.shareAlbum.domain.group.repository.GroupRepository;
import shareAlbum.shareAlbum.domain.group.repository.InvitationRepository;
import shareAlbum.shareAlbum.domain.group.repository.MyGroupRepository;
import shareAlbum.shareAlbum.domain.member.entity.Member;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MyGroupDto;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;
import shareAlbum.shareAlbum.global.redis.RedisService;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService{


    private final GroupRepository groupRepository;
    private final MyGroupRepository myGroupRepository;
    private final MemberRepository memberRepository;
    private final InvitationRepository invitationRepository;
    private final RedisService redisService;

    @Override
    @Transactional
    public void createGroup(GroupCreateDto groupCreateDto) {
        GroupList groupList = GroupList.builder()
                .groupTitle(groupCreateDto.getGroupTitle())
                .groupCategory(groupCreateDto.getGroupCategory())
                .createBy(groupCreateDto.getNickname())
                .build();
        groupRepository.save(groupList);
        //회원가입이 된 회원이 그룹을 만들때
        Member member = memberRepository
                .findByNickname(groupList.getCreateBy()).orElseThrow(()-> new RuntimeException("회원 정보가 존재하지 않습니다."));

        MyGroup myGroup = MyGroup.builder()
                .member(member)
                .groupList(groupList)
                .build();
        myGroupRepository.save(myGroup);

        //redis 업데이트
        if(redisService.findMemberInfoInRedis(groupCreateDto.getNickname()) != null){
            redisService.addNewGroupToRedis(member.getNickname(),myGroup,groupList);
        }
    }

    @Override
    public void inviteGroup(GroupInvitationDto groupInvitation) {
        Member inviterMember = memberRepository.findByNickname(groupInvitation.getInviterId()).orElseThrow(() -> new NoSuchElementException());
        Member receiverMember = memberRepository.findByNickname(groupInvitation.getInviterId()).orElseThrow(() -> new NoSuchElementException());
        Invitation invitation = Invitation.builder()
                .inviterId(groupInvitation.getInviterId())
                .receiverId(groupInvitation.getReceiverId())
                .groupList(groupRepository.findById(groupInvitation.getGroupId()).orElseThrow(()->new RuntimeException()))
                .invitation_status(InvitationStatus.PROCESS)
                .build();
        invitationRepository.save(invitation);
    }

    @Override
    public void acceptGroupInvitation(GroupInvitationDto groupInvitationDto) {
        Invitation invitation = invitationRepository.findInvitationById(groupInvitationDto.getInvitationId()).orElseThrow(()-> new NoSuchElementException());
        if(groupInvitationDto.getInvitationStatus().equals(InvitationStatus.ACCEPT)){
            MyGroup myGroup = MyGroup.builder()
                    .member(memberRepository.findByNickname(groupInvitationDto.getReceiverId()).orElseThrow(()-> new NoSuchElementException("회원정보가 없습니다")))
                    .groupList(groupRepository.findById(invitation.getGroupList().getId()).orElseThrow(()-> new RuntimeException()))
                    .build();
            System.out.println("myGroup.toString() = " + myGroup.toString());

            myGroupRepository.save(myGroup);
            invitation.changeStatus(InvitationStatus.ACCEPT);
            invitationRepository.save(invitation);

            redisService.acceptInvitationToRedis(groupInvitationDto,myGroup,invitation);

        }else {
            System.out.println("에러 발생");
        }
    }

    @Override
    public void rejectGroupInvitation(GroupInvitationDto groupInvitationDto) {
//        Invitation invitation = invitationRepository.findInvitationById(groupAcceptionDto.getInvitationId()).orElseThrow(()-> new NoSuchElementException());
//        if(groupAcceptionDto.getInvitationStatus().equals(InvitationStatus.ACCEPT)){
//            MyGroup myGroup = MyGroup.builder()
//                    .member(memberRepository.findByNickname(groupAcceptionDto.getReceiverId()).orElseThrow(()-> new NoSuchElementException("회원정보가 없습니다")))
//                    .groupList(groupRepository.findById(groupAcceptionDto.getGroupId()))
//                    .build();
//            myGroupRepository.save(myGroup);
//            invitation.changeStatus(InvitationStatus.ACCEPT);
//            invitationRepository.save(invitation);
//        }else if(groupAcceptionDto.getInvitationStatus().equals(InvitationStatus.REJECT)){
//            invitation.changeStatus(InvitationStatus.REJECT);
//            invitationRepository.save(invitation);
//        }
    }
}
