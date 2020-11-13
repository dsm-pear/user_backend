package com.dsmpear.main.entity.member;

import com.dsmpear.main.entity.team.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer teamId;

    @Column(length = 30,nullable = false)
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;
}
