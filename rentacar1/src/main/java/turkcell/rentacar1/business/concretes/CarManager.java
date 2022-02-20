package turkcell.rentacar1.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import turkcell.rentacar1.business.abstracts.CarService;
import turkcell.rentacar1.business.dtos.ListCarDto;
import turkcell.rentacar1.business.requests.CreateCarRequest;
import turkcell.rentacar1.business.requests.DeleteCarRequest;
import turkcell.rentacar1.business.requests.UpdateCarRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.mapping.ModelMapperService;
import turkcell.rentacar1.dataAccess.abstracts.CarDao;
import turkcell.rentacar1.entities.concretes.Car;

@Service
public class CarManager implements CarService{

		private CarDao carDao;
		private ModelMapperService modelMapperService;
		
	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
			super();
			this.carDao = carDao;
			this.modelMapperService = modelMapperService;
		}

	@Override
	public List<ListCarDto> getAll() {
		var result = this.carDao.findAll();
		List<ListCarDto> response = result.stream().map(car->this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public void add(CreateCarRequest createCarRequest) throws BusinessException {
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		if( !checkIfCarId(car.getCarId())) {
			
		}else {
			this.carDao.save(car);
		}
		
		
	}

	@Override
	public ListCarDto getByCarId(int carId) throws BusinessException {
		
		var result = this.carDao.getByCarId(carId);
		if(result!=null) {
			ListCarDto response = this.modelMapperService.forDto().map(result, ListCarDto.class);
		return response;
		}
		throw new BusinessException("Bu id kullanılmamaktadır.");	
	}

	@Override
	public void delete(DeleteCarRequest deleteCarRequest) throws BusinessException {
		
		Car car=this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		if(!checkIfCarId(car.getCarId())) {
		}else {
			this.carDao.deleteById(car.getCarId());
		}
		
	}

	@Override
	public void update(UpdateCarRequest updateCarRequest) throws BusinessException {
		
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		
		if( !checkIfCarId(car.getCarId())|| !checkIfDailyPrice(car.getDailyPrice())||!checkIfModelYear(car.getModelYear())||!checkIfDescription(car.getDescription())) {
			
				
		}else {
			this.carDao.save(car);
		}
		
	}
	
	private boolean checkIfCarId(int carId)throws BusinessException{
		if(this.carDao.getByCarId(carId)==null) {
			throw new BusinessException("Böyle bir id mevcut değil.");
		}
		return true;
		
	}
	private boolean checkIfDailyPrice(double dailyPrice) throws BusinessException {
		
		
		if(dailyPrice==0){
			throw new BusinessException("Fiyat 0 olamaz.");
		}
		return true;
		
	}
	
	private boolean checkIfModelYear(int modelYear) throws BusinessException {
		
		
		if(modelYear==0){
			throw new BusinessException("Arabanın üretim tarihi 0 olamaz.");
		}
		return true;
		
	}
	private boolean checkIfDescription(String description) throws BusinessException {
		if(org.apache.commons.lang3.StringUtils.isBlank(description)) {
			throw new BusinessException("Açıklama boş olamaz.");
		} return true;
	}
	
	
	
	
	
	
	
	
	
	

}
