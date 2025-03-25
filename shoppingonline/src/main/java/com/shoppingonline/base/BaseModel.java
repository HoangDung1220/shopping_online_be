package com.shoppingonline.base;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) 
@Getter
@Setter
public abstract class BaseModel {
	@CreatedBy
	private String createBy;
	
	@CreatedDate
	private LocalDateTime createAt;
	
	@LastModifiedBy
	private String updatedBy;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
}
