package pcy.study.simpleboard.reply.db;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import pcy.study.simpleboard.common.db.BaseTimeEntity;
import pcy.study.simpleboard.common.db.Status;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@SQLRestriction("status = 'REGISTERED'")
public class Reply extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;
    private String userName;
    private String password;
    private String title;
    private String contents;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR")
    private Status status;

    @Builder
    private Reply(
            Long postId,
            String userName,
            String password,
            String title,
            String contents
    ) {
        this.postId = postId;
        this.userName = userName;
        this.password = password;
        this.title = title;
        this.contents = contents;
        this.status = Status.REGISTERED;
    }
}
