package turkcell.rentacar1.business.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
	
private int additionalServiceId;
	
	@NotNull
	@Size(min = 1, max=250)
	private String additionalServiceName;
	
	@NotNull
	@Positive
	private double additionalPrice;

}
