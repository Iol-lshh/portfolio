package com.ltns.rest_area.domain.post;

import com.ltns.rest_area.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostVO implements VO {
	
	/*��û Ÿ��*/
	private String type;	//count, one, list, all
	
	/*������ ��û ������*/
	private String orderBy;	 //��û ���� ���	
	
	private int fromRow;	//������ ���� row
	private int numOfRows;	//�� �������� ����Ʈ�� dto ����
	
	private int pageNo;	//�ٲ� ������
	
	/*������ ��ü��*/
	private DTO dto;
	private List<DTO> list;
	private Object obj;
	
	/*������ ������*/
	private int post_id;
	private String post_title;
	private String post_contents;
	private int um_uid;
	private String um_username;
	private String post_regdate;
	private String ra_code;	//�ްԼ� ����
	private String post_reported;
}