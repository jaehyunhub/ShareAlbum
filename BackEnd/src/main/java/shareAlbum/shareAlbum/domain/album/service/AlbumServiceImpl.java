package shareAlbum.shareAlbum.domain.album.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shareAlbum.shareAlbum.domain.album.dto.AlbumRegisterDto;
import shareAlbum.shareAlbum.domain.album.entity.Album;
import shareAlbum.shareAlbum.domain.album.repository.AlbumRepository;
import shareAlbum.shareAlbum.domain.group.repository.GroupRepository;
import shareAlbum.shareAlbum.global.redis.RedisService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final GroupRepository groupRepository;
    private final RedisService redisService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Transactional
    public void uploadPicture(AlbumRegisterDto albumRegisterDto) {
        try {
            System.out.println("======1111111=====");
            System.out.println("uploadDir = " + uploadDir);
            System.out.println("======1111111=====");
            String fileName = UUID.randomUUID().toString().replace("-","")+ "_"
                    + albumRegisterDto.getImage().getOriginalFilename();
            String filePath = uploadDir + fileName;
            String dbFilePath = "/images/" + fileName;
            Path path = Paths.get(filePath);

            System.out.println("path = " + path);
            Files.write(path, albumRegisterDto.getImage().getBytes());

            Album album = Album.builder()
                    .imagePath(dbFilePath)
                    .content(albumRegisterDto.getContent())
                    .groupList(groupRepository.findById(albumRegisterDto.getGroupId()).orElseThrow())
                    .createBy(albumRegisterDto.getNickname())
                    .build();
            albumRepository.save(album);
            redisService.addAlbumToRedis(albumRegisterDto.getNickname(),album);

        } catch (IOException e) {
            System.out.println("e = " + e);
        }

    }
}