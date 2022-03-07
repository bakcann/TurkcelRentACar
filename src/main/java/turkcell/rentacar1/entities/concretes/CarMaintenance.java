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
@Table(name="car_maintenances")
public class CarMaintenance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="car_maintenance_id")
	private int maintenanceId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="returnDate")
	private LocalDate returnDate;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	

}
