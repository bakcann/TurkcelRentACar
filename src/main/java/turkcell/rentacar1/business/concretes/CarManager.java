package turkcell.rentacar1.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import turkcell.rentacar1.business.abstracts.CarService;
import turkcell.rentacar1.business.dtos.ListCarDto;
import turkcell.rentacar1.business.requests.CreateCarRequest;
import turkcell.rentacar1.business.requests.DeleteCarRequest;
import turkcell.rentacar1.business.requests.UpdateCarRequest;
import turkcell.rentacar1.core.concretes.BusinessException;
import turkcell.rentacar1.core.utilities.mapping.ModelMapperService;
import turkcell.rentacar1.core.utilities.results.DataResult;
import turkcell.rentacar1.core.utilities.results.ErrorDataResult;
import turkcell.rentacar1.core.utilities.results.ErrorResult;
import turkcell.rentacar1.core.utilities.results.Result;
import turkcell.rentacar1.core.utilities.results.SuccessDataResult;
import turkcell.rentacar1.core.utilities.results.SuccessResult;
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
	public DataResult<List<ListCarDto>> getAll() {
		var result = this.carDao.findAll();
		List<ListCarDto> response = result.stream()
				.map(car->this.modelMapperService.forDto()
						.map(car, ListCarDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) throws BusinessException {
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		
			this.carDao.save(car);
			return new SuccessResult("Araba eklendi");
	}

	@Override
	public DataResult<ListCarDto> getByCarId(int carId)  {
		
		var result = this.carDao.getByCarId(carId);
		if(result!=null) {
			ListCarDto response = this.modelMapperService.forDto().map(result, ListCarDto.class);
		 return new SuccessDataResult<ListCarDto>(response);
		}
		return new ErrorDataResult<ListCarDto>("Bu id kullanılmamaktadır.");	
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException {
		
		Car car=this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		if(checkIfCarId(car.getCarId())) {
			this.carDao.deleteById(car.getCarId());
			return new SuccessResult("Araba silindi");
				
		}return new ErrorResult("Araba silinemedi.");
		
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) throws BusinessException {
		
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		
		if( checkIfCarId(car.getCarId())&& checkIfDailyPrice(car.getDailyPrice())&&
				checkIfModelYear(car.getModelYear()) && checkIfDescription(car.getDescription())) {
			
			this.carDao.save(car);
			return new SuccessResult("Araba güncellendi.");
		
		}
		return new ErrorResult("Araba güncellenemedi.");
		
	}
	
	@Override
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
		Pageable pageable =PageRequest.of(pageNo-1, pageSize);
		
		List<Car> result = this.carDao.findAll(pageable).getContent(); 
		
		List<ListCarDto> response = result.stream()
				.map(car->this.modelMapperService.forDto()
						.map(car, ListCarDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction) {
		Sort sort = Sort.by(direction, "dailyPrice" );
		
		List<Car> result = this.carDao.findAll(sort); 
		
		List<ListCarDto> response = result.stream()
				.map(car->this.modelMapperService.forDto()
						.map(car, ListCarDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDto>>(response);
	}

	@Override
	public DataResult<List<ListCarDto>> getByDailyPriceLessThanEqual(double dailyPrice) {
		
		List<Car> result =this.carDao.getByDailyPriceLessThanEqual(dailyPrice);
		
		List<ListCarDto> response = result.stream()
				.map(car->this.modelMapperService.forDto()
						.map(car, ListCarDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDto>>(response);
	}
	@Override
	public boolean checkIfCarId(int carId)throws BusinessException{
		if(this.carDao.getByCarId(carId)!=null) {
			return true;
		}
		throw new BusinessException("Böyle bir id mevcut değil.");
		
		
	}
	
	private boolean checkIfDailyPrice(double dailyPrice) throws BusinessException {
		
		
		if(dailyPrice<=0){
			throw new BusinessException("Fiyat 0 olamaz.");
		}
		return true;
		
	}
	
	private boolean checkIfModelYear(int modelYear) throws BusinessException {
		
		
		if(modelYear<=0){
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
