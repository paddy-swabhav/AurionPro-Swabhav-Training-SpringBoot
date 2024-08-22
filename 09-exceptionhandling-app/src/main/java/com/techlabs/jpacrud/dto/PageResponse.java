package com.techlabs.jpacrud.dto;

import java.util.List;

import com.techlabs.jpacrud.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class PageResponse<T> {

	private int totalPages;
	private long totalElements;
	private int size;
	private List<T> content;
	private boolean isLastPage;
}
