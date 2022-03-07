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
import turkcell.rentacar1.business.dtos.GetListRentalDto;
import turkcell.rentacar1.business.dtos.ListRentalDto;
import turkcell.rentacar1.business.requests.CreateRentalRequest;
import turkcell.rentacar1.business.requests.DeleteRentalRequest;
import turkcell.rentacar1.business.requests.UpdateRentalRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.mapping.ModelMapperService;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;
import turkcell.rentacar1.core.utilities.results.SuccessDataResult;
import turkcell.rentacar1.dataAccess.abstracts.RentalDao;
import turkcell.rentacar1.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService{
	
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private CarMaintenanceService carMaintenanceService;
	
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService,
			CarMaintenanceService carMaintenanceService) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.carMaintenanceService = carMaintenanceService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) throws BusinessException {
		
		return null;
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {
		
		return null;
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException {
	
		return null;
	}

	@Override
	public DataResult<List<ListRentalDto>> getAll() {
		
		var result = this.rentalDao.findAll();
		List<ListRentalDto> response =result.stream()
				.map(rental->this.modelMapperService.forDto()
						.map(rental, ListRentalDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public DataResult<GetListRentalDto> getByRentalId(int rentId) {
		
		return null;
	}

	@Override
	public DataResult<List<ListRentalDto>> getAllPaged(int pageNo, int pageSize) {
		
		Pageable pageable =PageRequest.of(pageNo-1, pageSize);
		
		List<Rental> result = this.rentalDao.findAll(pageable).getContent(); 
		
		List<ListRentalDto> response = result.stream()
				.map(rental->this.modelMapperService.forDto()
						.map(rental, ListRentalDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public DataResult<List<ListRentalDto>> getAllSorted(Direction direction) {
		
		Sort sort = Sort.by(direction, "rentReturnDate" );
		
		List<Rental> result = this.rentalDao.findAll(sort); 
		
		List<ListRentalDto> response = result.stream()
				.map(rental->this.modelMapperService.forDto()
						.map(rental, ListRentalDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public DataResult<ListRentalDto> getByCar_CarId(int carId) {
		
		return null;
	}

}
