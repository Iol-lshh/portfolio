package com.ltns.rest_area.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AjaxResult {
	private int count; //������ ����
	private String status; //ó�� ���
	private String message; //��� �޼���
	
	private Object obj; //��Ÿ ���� ������ ���۽�
}