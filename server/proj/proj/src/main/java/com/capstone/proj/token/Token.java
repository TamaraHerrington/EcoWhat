package com.capstone.proj.token;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Token {

    private Long userId;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private String secret;

}
