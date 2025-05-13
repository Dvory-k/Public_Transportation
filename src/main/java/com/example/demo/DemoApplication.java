package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Converters.BusConverter;
import com.example.demo.Converters.DriverConverter;
import com.example.demo.Converters.LineConverter;
import com.example.demo.Converters.StationConverter;
import com.example.demo.Converters.StationLineConverter;
import com.example.demo.Converters.TravelConverter;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Bean יצירת
	// Bean-אחרי שמגדירים מחלקה מסוימת להיות כ
	// (Autowired) יוכל להזריק אותו למי שיבקש spring container-ה
	// @Service,@Repository-Bean הן אנוטציות שמסמנות יצירת
	@Bean
	public BusConverter getBusConverter() {
		return new BusConverter();
	}

	@Bean
	public DriverConverter getDriverConverter() {
		return new DriverConverter();
	}

	@Bean
	public LineConverter getLineConverter() {
		return new LineConverter();
	}
		@Bean
	public StationLineConverter getsStationLineConverter() {
		return new StationLineConverter();
	}
		@Bean
	public StationConverter getsStationConverter() {
		return new StationConverter();
	}

		@Bean
	public TravelConverter getTravelConverter() {
		return new TravelConverter();
	}
}


