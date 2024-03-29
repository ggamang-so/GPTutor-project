package com.ggamangso.gptutorproject.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditingFields {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)//데이트 타임 포메팅
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime created_at; //생성일지

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)//데이트 타임 포메팅
    @CreatedDate
    @Column(nullable = false)
    protected LocalDateTime updated_at; // 수정일시

}
