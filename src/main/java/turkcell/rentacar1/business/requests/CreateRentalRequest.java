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
public class CreateRentalRequest {
	
	@NotNull
	private LocalDate rentDate;
	
	@Positive
	private int carId;
	
	@Positive
	private int customerId;
	

}
