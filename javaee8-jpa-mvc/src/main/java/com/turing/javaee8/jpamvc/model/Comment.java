package com.turing.javaee8.jpamvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Comment extends BaseEntity{

	@Column
	String comment;
}
