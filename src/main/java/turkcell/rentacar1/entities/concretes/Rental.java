package turkcell.rentacar1.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rentals")
public class Rental {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rental_id")
	private int rentId;
	
	@Column(name="rental_date")
	private LocalDate rentDate;
	
	@Column(name="rental_return_date")
	private LocalDate rentReturnDate;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	
	

}
