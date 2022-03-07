package turkcell.rentacar1.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import turkcell.rentacar1.business.abstracts.CarMaintenanceService;
import turkcell.rentacar1.business.abstracts.CarService;
import turkcell.rentacar1.business.abstracts.RentalService;
import turkcell.rentacar1.business.dtos.GetListCarMaintenanceDto;
import turkcell.rentacar1.business.dtos.ListCarMaintenanceDto;
import turkcell.rentacar1.business.requests.CreateCarMaintenanceRequest;
import turkcell.rentacar1.business.requests.DeleteCarMaintenanceRequest;
import turkcell.rentacar1.business.requests.UpdateCarMaintenanceRequest;
import turkcell.rentacar1.core.utilities.mapping.ModelMapperService;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;
import turkcell.rentacar1.core.utilities.results.SuccessDataResult;
import turkcell.rentacar1.dataAccess.abstracts.CarMaintenanceDao;
import turkcell.rentacar1.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {
	
	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private RentalService rentalService;

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		
		return null;
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		
		return null;
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
	
		return null;
	}

	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		var result = this.carMaintenanceDao.findAll();
		List<ListCarMaintenanceDto> response =result.stream()
				.map(maintenance->this.modelMapperService.forDto()
						.map(maintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
		
	}

	@Override
	public DataResult<GetListCarMaintenanceDto> getByCarMaintenanceId(int maintenanceId) {
		
		return null;
	}

	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAllPaged(int pageNo, int pageSize) {
		
		Pageable pageable =PageRequest.of(pageNo-1, pageSize);
		
		List<CarMaintenance> result = this.carMaintenanceDao.findAll(pageable).getContent(); 
		
		List<ListCarMaintenanceDto> response = result.stream()
				.map(maintenance->this.modelMapperService.forDto()
						.map(maintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
	}

	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAllSorted(Direction direction) {
		
		Sort sort = Sort.by(direction, "returnDate" );
		
		List<CarMaintenance> result = this.carMaintenanceDao.findAll(sort); 
		
		List<ListCarMaintenanceDto> response = result.stream()
				.map(maintenance->this.modelMapperService.forDto()
						.map(maintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response);
	}

	@Override
	public DataResult<GetListCarMaintenanceDto> getByCar_CarId(int carId) {
		
		return null;
	}

}
