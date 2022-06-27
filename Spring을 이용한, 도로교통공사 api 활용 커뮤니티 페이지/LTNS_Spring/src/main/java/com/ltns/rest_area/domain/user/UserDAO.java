package com.ltns.rest_area.domain.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.post.CommentDTO;
import com.ltns.rest_area.domain.post.PostDTO;
import com.ltns.rest_area.domain.restarea.FoodMenuDTO;
import com.ltns.rest_area.domain.restarea.GasStationDTO;
import com.ltns.rest_area.domain.restarea.RestAreaDTO;

public interface UserDAO extends DAO {

	List<UserDTO> findByNickname(UserDTO user);
	int updateAllByUsername(UserDTO user);
	int deleteByPost_like(UserDTO user); 
	int deleteByPOST(UserDTO user); 
	int deleteByFM_like(UserDTO user); 
	int deleteByGS_like(UserDTO user); 
	int deleteByRA_like(UserDTO user); 
	int deleteByComments(UserDTO user); 
	
	int deleteStrLike(@Param("uid")long uid, @Param("table")String table,@Param("column")String column,@Param("strCode")String []strCode);
	int deleteNumLike(@Param("uid")long uid, @Param("table")String table,@Param("column")String column,@Param("numCode") int []numCode);
	
	List<LikeDTO> findByRA_like_All(UserDTO user);
	List<LikeDTO> findByGS_like_All(UserDTO user);
	List<LikeDTO> findByFM_like_All(UserDTO user);
	List<LikeDTO> findByPost_like_All(UserDTO user);
	List<PostDTO> findByPost_All(UserDTO user);
	List<CommentDTO> findByComment_All(UserDTO user);
		
	List<LikeDTO> findByRA_like(@Param("user") UserDTO user, @Param("from")int from, @Param("to")int to);
	List<LikeDTO> findByGS_like(@Param("user") UserDTO user, @Param("from")int from, @Param("to")int to);
	List<LikeDTO> findByFM_like(@Param("user") UserDTO user, @Param("from")int from, @Param("to")int to);
	List<LikeDTO> findByPost_like(@Param("user") UserDTO user, @Param("from")int from, @Param("to")int to);
	List<PostDTO> findByPost(@Param("user") UserDTO user, @Param("from")int from, @Param("to")int to);
	List<CommentDTO> findByComments(@Param("user") UserDTO user, @Param("from")int from, @Param("to")int to);
	 
	List<FoodMenuDTO> findByFoodMenu(LikeDTO like);
	List<GasStationDTO> findByGasStation(LikeDTO like);
	List<RestAreaDTO> findByRestArea(LikeDTO like);
	List<PostDTO> findByLikePost(LikeDTO like);
	
	
}
