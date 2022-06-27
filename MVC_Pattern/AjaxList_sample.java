package com.ltns.rest_area.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ltns.rest_area.domain.memberInfo.memberInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AjaxList extends AjaxResult{
	
	int totalCnt;	//�� dto ����

	int pageNo;		//���� ������
	int numOfRows; 	//�� �������� ����Ʈ�� dto ����
	int totalPage;	//�� ������ ����
	
	List<DTO> list;	//dto ������


	int pagenationPage;	//���������̼ǿ� ǥ���� ������ ����
	int writePages ; // list �� 
	
	@Override
	public String toString() {
		return "AjaxList (count=" + getCount() + ", status=" + getStatus() + ", message=" + getMessage()
				+ "totalCnt=" + totalCnt + ", pageNo=" + pageNo + ", numOfRows=" + numOfRows + ", totalPage="
				+ totalPage + ", list=" + list + ", pagenationPage=" + pagenationPage + ", writePages=" + writePages
				+ ")";
	}
}