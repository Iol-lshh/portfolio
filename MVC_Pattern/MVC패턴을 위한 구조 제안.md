���� ����
==============
    
���α׷� �ҽ�
--------
>src/main/java/com/(���� ��)   
>>domain ������(��)   
>>>DAO.java(�������̽�)   
>>>>select(VO vo)   
>>>>insert(VO vo)   
>>>>delete(VO vo)   
>>>>update(VO vo)   
>>>>=>���� ������ ����, ������ batch �̿��� ���� ��õ   
>>>   
>>>DTO.java(�����������̽�)   
>>>: ������ Ȱ�� �뵵   
>>>   
>>>VO.java(�����������̽�)   
>>>: DB���� ������ ��û��   
>>>���� : https://github.com/markhong93/portfolio/blob/main/MVC_Pattern/sample_PostVO.java   
>>>���� �������̽��� Ȱ���ϴ� ���� : ������, �������� ���   
>>>
>>>AjaxResult.java   
>>>: REST response   
>>>���� : https://github.com/markhong93/portfolio/blob/main/MVC_Pattern/sample_AjaxResult
>>>
>>>AjaxList.java   
>>>: extends AjaxResult   
>>>���� ������ ���� ��   
>>>���� : https://github.com/markhong93/portfolio/blob/main/MVC_Pattern/sample_AjaxList
>>>   
>>>���� ���񽺿� �ʿ��� ��Ű��   
>>>>DAO, DTO, VO ���
>>>> ex) DAO.java(����) > PostDAO.java > PostDAO.xml (���� ���)
>>>>  (������ �˾Ƽ�, ��������(xml)�� ���� impl �ڹ�Ŭ������ ���� => impl��� �߰� �Ű�ü abstract Ŭ���� ���� ��õ-impl�� ������ �߻�޼ҵ���� ���� �������� �ʾƵ� �Ǽ�, �ܼ�����)
>>>> ex) DAO.java(����) > AbstractDAO.java(����) > PostDAO.java (�ڹ� ���� ���)
>>>> vo�� ��� type �ִ°� ��õ! // type : count, one, list, all ��..   
>>>> dto�� ��� �翬�Ѱ�����, builder ���� Ȱ�� ���� ��õ   
>>   
>>controller ��Ʈ�ѷ�   
>>   
>>service ����   
   
�׽�Ʈ ���α׷� �ҽ�   
---------
>src/test/java/com/(���� ��)   
   
   
   
������   
---------
>WEB-INF   
>>spring   
>>>>root-context (���� ��� �����ʿ�)   
>>>>appServlet   
>>>>jdbc   
>>>>>jdbc-context.xml   
>>>>>����� ��� ���� ��������������   
>>>>>(ex mybatis-context.xml or jpa-context.xml ��..)   
>>   
>>views   
>>>>���� jsp ���� ������ ���� ����. �˾Ƽ�...   
    
������ ���ҽ� ����
---------
>webapp/resources   
>>css   
>>>���� �������� �ʿ��� css ���� ����. �˾Ƽ�...    
>>   
>>js   
>>>���� �������� �ʿ��� js ���� ����. �˾Ƽ�...   

    
���� ����
--------- 
>src/main/resources/com/(���� ��)   
>>domain   
>>>���� �����ΰ� ������ ������ �� ���   
>>>>DAO ��������   
