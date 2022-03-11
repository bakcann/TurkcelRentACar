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

import turkcell.rentacar1.business.abstracts.CarMaintenanceService;
import turkcell.rentacar1.business.abstracts.CarService;
import turkcell.rentacar1.business.abstracts.RentalService;
import turkcell.rentacar1.business.dtos.GetListCarMaintenanceDto;
import turkcell.rentacar1.business.dtos.ListCarMaintenanceDto;
import turkcell.rentacar1.business.requests.CreateCarMaintenanceRequest;
import turkcell.rentacar1.business.requests.DeleteCarMaintenanceRequest;
import turkcell.rentacar1.business.requests.UpdateCarMaintenanceRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.mapping.ModelMapperService;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.Result;
import turkcell.rentacar1.core.utilities.results.SuccessDataResult;
import turkcell.rentacar1.core.utilities.results.SuccessResult;
import turkcell.rentacar1.dataAccess.abstracts.CarMaintenanceDao;
import turkcell.rentacar1.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {
	
	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private RentalService rentalService;
	
	@Lazy
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,
			CarService carService, RentalService rentalService) {
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.rentalService = rentalService;
	}


	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException {
		this.carService.checkIfCarId(createCarMaintenanceRequest.getCarId());
		this.rentalService.checkIfRentCarReturnDate(createCarMaintenanceRequest.getCarId());
		checkIfCarMaintenceReturnDate(createCarMaintenanceRequest.getCarId());
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult("CarMaintenance eklendi");
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException {
		checkIfCarMaintenanceId(deleteCarMaintenanceRequest.getMaintenanceId());
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(deleteCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.deleteById(carMaintenance.getMaintenanceId());
		
		return new SuccessResult("CarMaintenance silindi");
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {
		checkIfCarMaintenanceId(updateCarMaintenanceRequest.getMaintenanceId());
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		return new SuccessResult("CarMaintenance güncellendi");
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
	public DataResult<GetListCarMaintenanceDto> getByCarMaintenanceId(int maintenanceId) throws BusinessException {
		checkIfCarMaintenanceId(maintenanceId);
		var result = this.carMaintenanceDao.getById(maintenanceId);
		GetListCarMaintenanceDto response = this.modelMapperService.forDto().map(result, GetListCarMaintenanceDto.class);
		
		
		return new SuccessDataResult<GetListCarMaintenanceDto>(response);
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
	public List<GetListCarMaintenanceDto> getByCar_CarId(int carId) throws BusinessException {
		List<CarMaintenance> result = this.carMaintenanceDao.getByCar_CarId(carId);
		if(result.isEmpty()) {
			throw new BusinessException("Araç bakımda değil.");
		}
		List<GetListCarMaintenanceDto> response = result.stream().map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, GetListCarMaintenanceDto.class)).collect(Collectors.toList());
		
		return response;
	}


	@Override
	public boolean checkIfCarMaintenanceId(int maintenanceId) throws BusinessException {
		var result = this.carMaintenanceDao.getByMaintanenceId(maintenanceId);
		if(result!=null) {
			return true;
		}
		throw new BusinessException("Geçerli Id giriniz.");
	}


	@Override
	public boolean checkIfCarMaintenceReturnDate(int carId) throws BusinessException {
		var result = this.carMaintenanceDao.getCarMaintenanceByCarCarIdAndReturnDateIsNull(carId);
		if(result!=null) {
			throw new BusinessException("Araç henüz bakımdan dönmedi.");
		}
		return true;
	}

}
