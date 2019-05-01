package bon.plan.views;

import org.springframework.data.jpa.repository.JpaRepository;

import bon.plan.entities.Category;

public interface CategoryView extends JpaRepository<Category, Long> {

}
