package bon.plan.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bon.plan.entities.Produit;
import bon.plan.entities.User;

public interface ProduitView extends JpaRepository<Produit, Long> {


	@Query("SELECT p FROM Produit p WHERE p.planId like :x ")
	public List<Produit> getbyplan(@Param("x")Long planId );
}
