package br.com.cambio_service.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cambio_service.model.Cambio;
import br.com.cambio_service.repository.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Cambio endpoint")  // implementação Swagger
@RestController
@RequestMapping("cambio-service")
public class CambioController {
	
	private Logger logger = LoggerFactory.getLogger(CambioController.class);
	
	@Autowired
	private Environment environment;
	@Autowired
	private CambioRepository repository;

	//http://localhost:8000/cambio-service/5/USD/BRL
	@Operation(description = "Get Cambio from currency!")
	@GetMapping(value = "/{amount}/{from}/{to}")
	public Cambio getCambio(
			@PathVariable("amount") BigDecimal amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to
			) {
		
		logger.info("getCambio is called with -> {}, {} and {} ", amount, from, to);
		
		var cambio = repository.findByFromAndTo(from, to);
		if (cambio == null) throw new RuntimeException("Currency Unsupported");
		
		var port = environment.getProperty("local.server.port");
		
		BigDecimal conversionFactor = cambio.getConversionFactor(); // setar valor convertido
		BigDecimal convertedValue = conversionFactor.multiply(amount); // converter o valor do cambio pela quantidade setada
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));  // arredonda o valor
		cambio.setEnvironment(port); // seta a porta
		
		return cambio;
	}
}
