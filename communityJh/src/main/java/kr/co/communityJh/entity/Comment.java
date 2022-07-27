package kr.co.communityJh.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "COMMENT_SEQ_GENERATOR",
        sequenceName = "SEQ_COMMENT", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)
@Entity(name = "TB_COMMENT")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "userId")
    private Account account;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "boardId")
    private Board board;

}
