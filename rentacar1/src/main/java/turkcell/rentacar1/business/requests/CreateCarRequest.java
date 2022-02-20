package turkcell.rentacar1.business.requests;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	private int carId;
	private double dailyPrice;
	private int modelYear;
	private String description;
	
	private int brandId;
	private int colorId;

}
