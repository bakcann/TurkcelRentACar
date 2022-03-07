package turkcell.rentacar1.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import turkcell.rentacar1.entities.concretes.Rental;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer> {
	
	Rental getByRentalId(int rentId);
	
	List<Rental> getByCar_CarId(int carId);
	
	List<Rental> getByCustomer_CustomerId(int customerId);
	

}
