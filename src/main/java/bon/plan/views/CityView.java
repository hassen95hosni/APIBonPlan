package bon.plan.views;

import org.springframework.data.jpa.repository.JpaRepository;

import bon.plan.entities.City;

public interface CityView extends JpaRepository<City, Long> {

}
