package turkcell.rentacar1.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {
	
	private int rentId;
	
	private LocalDate rentDate;
	
	private LocalDate rentReturnDate;
	
	private int carId;
	
	private int customerId;

}
