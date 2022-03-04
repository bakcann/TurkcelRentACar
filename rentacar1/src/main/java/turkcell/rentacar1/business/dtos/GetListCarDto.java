package turkcell.rentacar1.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListCarDto {
	
	private int CarId;
	private double dailyPrice;
	private int modelYear;
	private String description;
	
	private String brandName;
	private String colorName;

}
