package turkcell.rentacar1.business.requests;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {
	
	@NotNull
	@Positive
	private int maintenanceId;
	
	private String description;
	private LocalDate returnDate;
	
	@NotNull
	private int carId;

}
