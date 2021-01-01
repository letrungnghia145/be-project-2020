package com.nghiale.api.mapper;

import org.springframework.stereotype.Service;

import com.nghiale.api.dto.EvaluateDTO;
import com.nghiale.api.model.Customer;
import com.nghiale.api.model.Evaluate;
import com.nghiale.api.utils.Converter;

@Service
public class EvaluateMapper implements Mapper<Evaluate, EvaluateDTO> {

	@Override
	public Evaluate convertToBO(EvaluateDTO dto) {
		Evaluate evaluate = new Evaluate();
		Converter.convert(dto, evaluate);
		evaluate.setCustomer(new Customer(dto.getCustomerID()));
		return evaluate;
	}

	@Override
	public EvaluateDTO convertToDTO(Evaluate bo) {
		Long customerID = bo.getCustomer().getId();
		return new EvaluateDTO(bo.getEvaluateCode(), bo.getComment(), bo.getValue(), customerID);
	}

}
