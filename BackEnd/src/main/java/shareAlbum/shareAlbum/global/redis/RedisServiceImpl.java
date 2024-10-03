package shareAlbum.shareAlbum.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import shareAlbum.shareAlbum.domain.album.entity.Album;
import shareAlbum.shareAlbum.domain.group.dto.GroupInvitationDto;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;
import shareAlbum.shareAlbum.domain.group.entity.Invitation;
import shareAlbum.shareAlbum.domain.group.entity.MyGroup;
import shareAlbum.shareAlbum.domain.member.query.mainPage.AlbumDto;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MyGroupDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public MemberInfoDto findMemberInfoInRedis(String nickname) {
        MemberInfoDto memberInfoDto = (MemberInfoDto) redisTemplate.opsForValue().get(nickname);
        return memberInfoDto;
    }

    @Override
    public void addNewGroupToRedis(String nickname,MyGroup myGroup, GroupList groupList) {
        MemberInfoDto memberInfo = Optional.ofNullable((MemberInfoDto) redisTemplate.opsForValue().get(nickname))
        .orElseThrow(() -> new RuntimeException("Redis에 저장된 회원 정보가 없습니다"));
        MyGroupDto myNewGroupDto = new MyGroupDto(myGroup.getId(),groupList.getGroupTitle(),groupList.getId());
        memberInfo.getMyGroupList().add(myNewGroupDto);

        redisTemplate.opsForValue().set(nickname,memberInfo);
    }

    @Override
    public void addAlbumToRedis(String nickname, Album album) {
        MemberInfoDto memberInfo = Optional.ofNullable((MemberInfoDto) redisTemplate.opsForValue().get(nickname))
                .orElseThrow(() -> new RuntimeException("Redis에 저장된 회원 정보가 없습니다"));

        AlbumDto newAlbum = AlbumDto.builder()
                .id(album.getId())
                .imagePath(album.getImagePath())
                .groupListId(album.getGroupList().getId())
                .content(album.getContent())
                .build();

        Long groupListId = newAlbum.getGroupListId();//Map<Long,List<AlbumDto>> myAlbum의 key부분
        List<AlbumDto> albumList = memberInfo.getMyAlbum().computeIfAbsent(groupListId, k -> new ArrayList<>());
        albumList.add(newAlbum);
        redisTemplate.opsForValue().set(nickname,memberInfo);
    }
    @Override
    public void acceptInvitationToRedis(GroupInvitationDto groupInvitationDto, MyGroup myGroup, Invitation invitation) {
        try{
            MemberInfoDto memberInfo = Optional.ofNullable((MemberInfoDto) redisTemplate.opsForValue().get(groupInvitationDto.getReceiverId()))
                    .orElseThrow(() -> new RuntimeException("Redis에 저장된 회원 정보가 없습니다"));
            MyGroupDto myNewGroupDto = new MyGroupDto(myGroup.getId(),groupInvitationDto.getGroupTitle(),invitation.getGroupList().getId());

            GroupInvitationDto newGroupInvitationDto = GroupInvitationDto.builder()
                    .invitationId(groupInvitationDto.getInvitationId())
                    .invitationStatus(groupInvitationDto.getInvitationStatus())
                    .groupTitle(groupInvitationDto.getGroupTitle())
                    .inviterId(invitation.getInviterId())
                    .receiverId(invitation.getReceiverId())
                    .groupId(invitation.getGroupList().getId())
                    .build();

            // 기존 초대 정보 리스트 가져오기
            List<GroupInvitationDto> groupInvitationList = memberInfo.getGroupInvitationList();
            // 기존 초대 정보에서 해당 초대 삭제
            groupInvitationList.removeIf(invitationDto -> invitationDto.getInvitationId().equals(newGroupInvitationDto.getInvitationId()));
            // 새로운 초대 정보 추가
            groupInvitationList.add(newGroupInvitationDto);

            // Redis에 다시 저장
            redisTemplate.opsForValue().set(groupInvitationDto.getReceiverId(), memberInfo);

            System.out.println("Invitation status updated and saved to Redis.");


        }catch (RuntimeException e){
            e.printStackTrace();

        }
    }
}
