package com.shoppingonline.security;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="persistent_logins")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersistentLoginEntity {
	
	@Id
	private String series;
	
	private String token;
	
	private Timestamp lastUsed;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
}
