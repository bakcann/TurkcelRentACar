package turkcell.rentacar1.dataAccess.abstracts;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import turkcell.rentacar1.entities.concretes.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Integer>{
	
	Car getByCarId(int carId);
	Car getByDailyPrice(double dailyPrice);
	Car getByModelYear(int modelYear);
	Car getByDescription(String description);
	List<Car> getByDailyPriceLessThanEqual(double dailyPrice);
	

}
