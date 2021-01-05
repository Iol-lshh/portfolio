package com.ltns.rest_area.domain.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.VO;

public class AuthDAOImpl implements AuthDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override 
    public int insertByObject(Object obj) {
        long uid = (long) obj;
        return sqlSession.getMapper(AuthDAO.class).insertByObject(uid);
    }
    

    @Override
    public int insertByDTO(DTO dto) {
    	UserAuthDTO userAuth = (UserAuthDTO)dto;
        return sqlSession.getMapper(AuthDAO.class).insertByDTO(userAuth);
    }


    @Override 
    public int deleteByObject(Object obj) {
        long uid = (long) obj;
        return sqlSession.getMapper(AuthDAO.class).deleteByObject(uid);
    }
    
    @Override
    public List<DTO> selectByObject(Object obj) {
    	 long uid = (long) obj;
        return sqlSession.getMapper(AuthDAO.class).selectByObject(uid);
    }
  
    @Override
    public List<DTO> selectByString(String s) {
        return sqlSession.getMapper(AuthDAO.class).selectByString(s);
    }
    
    @Override
    public int updateByObject(Object obj) {
    	UserAuthDTO userAuth = (UserAuthDTO)obj;
        return sqlSession.getMapper(AuthDAO.class).updateByObject(userAuth);
    }
    
	@Override
	public int deleteByUserAuth(UserAuthDTO userAuthDTO) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(AuthDAO.class).deleteByUserAuth(userAuthDTO);
	}
    
    @Override
    public List<DTO> selectAll() {
        return sqlSession.getMapper(AuthDAO.class).selectAll();
    }

    @Override
    public List<DTO> selectByInt(int i) {
        return null;
    }



    @Override
    public List<DTO> selectByDTO(DTO dto) {
        return null;
    }





    @Override
    public int updateByDTO(DTO dto) {
        return 0;
    }



    @Override
    public int deleteByInt(int i) {
        return 0;
    }

    @Override
    public int deleteAll() {
        return 0;
    }

	@Override
	public int insertAllByDTOs(List<DTO> dtos) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int updateAllByDTOs(List<DTO> dtos) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int deleteByString(String str) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCntByInt(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCntByString(String str) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCntByObject(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<DTO> selectFromRow(int from, int pagenationPage) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int selectCntByVO(VO vo) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<DTO> selectByVO(VO vo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int insertByVO(VO vo) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int updateByVO(VO vo) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int deleteByVO(VO vo) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int deleteByUid(int[] uids) {
		// TODO Auto-generated method stub
		return 0;
	}



	
}
