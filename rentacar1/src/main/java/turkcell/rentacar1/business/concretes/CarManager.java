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
			this.carDao.save(car);
		
		
	}

	@Override
	public ListCarDto getByCarId(int carId) throws BusinessException {
		
		var result = this.carDao.getByCarId(carId);
		if(result!=null) {
			ListCarDto response = this.modelMapperService.forDto().map(result, ListCarDto.class);
		return response;
		}
		throw new BusinessException("Bu id boş.");	
	}

	@Override
	public void delete(DeleteCarRequest deleteCarRequest) throws BusinessException {
		Car car = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		if(this.carDao.getByCarId(car.getCarId())==null) {
			throw new BusinessException("Böyle bir id mevcut değil.");
		}
		this.carDao.deleteById(car.getCarId());
		
	}

	@Override
	public void update(UpdateCarRequest updateCarRequest) throws BusinessException {
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		if(this.carDao.getByCarId(car.getCarId())==null) {
			throw new BusinessException("Böyle bir id mevcut değil.");
		}
		this.carDao.save(car);
		
	}
	
	
	

}
