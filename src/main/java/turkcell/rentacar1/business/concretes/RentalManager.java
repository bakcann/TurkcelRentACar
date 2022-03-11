package turkcell.rentacar1.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import turkcell.rentacar1.business.abstracts.AdditionalServiceService;
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
import turkcell.rentacar1.core.utilities.results.ErrorDataResult;
import turkcell.rentacar1.core.utilities.results.Result;
import turkcell.rentacar1.core.utilities.results.SuccessDataResult;
import turkcell.rentacar1.core.utilities.results.SuccessResult;
import turkcell.rentacar1.dataAccess.abstracts.RentalDao;
import turkcell.rentacar1.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService{
	
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private CarMaintenanceService carMaintenanceService;
	private AdditionalServiceService additionalServiceService;
	

	@Lazy
	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService,
			CarMaintenanceService carMaintenanceService, AdditionalServiceService additionalServiceService) {
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.carMaintenanceService = carMaintenanceService;
		this.additionalServiceService = additionalServiceService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) throws BusinessException {
		this.carService.checkIfCarId(createRentalRequest.getCarId());
		this.carMaintenanceService.checkIfCarMaintenceReturnDate(createRentalRequest.getCarId());
		this.additionalServiceService.getById(createRentalRequest.getAdditionalServiceId());
		checkIfRentCarReturnDate(createRentalRequest.getCarId());
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult("Araç kiralama başarılı");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {
		checkIfRentCarId(deleteRentalRequest.getRentId());
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalDao.deleteById(rental.getRentId());
		return new SuccessResult("RentACar silindi");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException {
		checkIfRentCarId(updateRentalRequest.getRentalId());
		this.additionalServiceService.getById(updateRentalRequest.getAdditionalServiceId());
		this.carService.checkIfCarId(updateRentalRequest.getCarId());
		
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		
	
		return new SuccessResult("RentACar güncellendi");
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
	public DataResult<GetListRentalDto> getByRentalId(int rentId) throws BusinessException {
		checkIfRentCarId(rentId);
		var result = this.rentalDao.getByRentalId(rentId);
		GetListRentalDto response = this.modelMapperService.forDto().map(result, GetListRentalDto.class);
		
		return new SuccessDataResult<GetListRentalDto>(response);
	}

	@Override
	public DataResult<List<ListRentalDto>> getAllPaged(int pageNo, int pageSize) {
		
		Pageable pageable =PageRequest.of(pageNo-1, pageSize);
		
		List<Rental> result = this.rentalDao.findAll(pageable).getContent(); 
		if(result==null) {
			return new ErrorDataResult<List<ListRentalDto>>("Liste boş");
		}
		
		List<ListRentalDto> response = result.stream()
				.map(rental->this.modelMapperService.forDto()
						.map(rental, ListRentalDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public DataResult<List<ListRentalDto>> getAllSorted(Direction direction) {
		
		Sort sort = Sort.by(direction, "rentReturnDate" );
		
		List<Rental> result = this.rentalDao.findAll(sort); 
		if(result==null) {
			return new ErrorDataResult<List<ListRentalDto>>("Liste boş");
		}
		
		List<ListRentalDto> response = result.stream()
				.map(rental->this.modelMapperService.forDto()
						.map(rental, ListRentalDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListRentalDto>>(response);
	}

	@Override
	public List<ListRentalDto> getByCar_CarId(int carId) throws BusinessException {
		List<Rental> result = this.rentalDao.getByCar_CarId(carId);
		if (result.isEmpty()) {
			throw new BusinessException("Arac  kiralama işlemi için uygundur.");
		}
		List<ListRentalDto> response = result.stream()
				.map(rental -> this.modelMapperService.forDto().map(rental, ListRentalDto.class))
				.collect(Collectors.toList());
		return response;
		
		
	}

	@Override
	public boolean checkIfRentCarId(int carId) throws BusinessException {
		var result =this.rentalDao.getByRentalId(carId);
		if(result!=null) {
			return true;
		}
		throw new BusinessException("Geçersiz Id .");
	}

	@Override
	public boolean checkIfRentCarReturnDate(int carId) throws BusinessException {
		
		var result = this.rentalDao.getRentalByCarCarIdAndReturnDateIsNull(carId);
		
		if(result!=null) {
			throw new BusinessException("Araba henüz kiralamadan dönmedi.");
		}
		return true;
		
		
	}

}
