package bon.plan.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bon.plan.entities.Plan;
import bon.plan.entities.Produit;

public interface PlanView extends JpaRepository<Plan, Long> {

	@Query("SELECT p FROM Plan p WHERE p.id like :x ")
	public List<Plan> getbycatg(@Param("x")Long categoryId );
}
