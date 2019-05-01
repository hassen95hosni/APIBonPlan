package bon.plan.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bon.plan.entities.User;

public interface UserView extends JpaRepository<User, Long> {

	
	@Query("SELECT u FROM User u WHERE u.email like :x AND u.password like :y")
	public User authent(@Param("x")String email  , @Param("y") String password);
}
