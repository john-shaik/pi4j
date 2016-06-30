package pi.saida.ledcontrol;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@Controller
public class SpringBootLedControl {
	
	GpioPinDigitalOutput pin = null;
	
	@RequestMapping("/")
	@ResponseBody
	public String greeting() {
		return "Hello World!!!";
	}
	
	@RequestMapping("/toggle")
	@ResponseBody
	public String toggle() {
		getPin().toggle();
		return "OK";
	}
	
	private GpioPinDigitalOutput getPin() {
		if( pin == null ) {
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "PinLED", PinState.LOW);
		}
		return pin;
	}
}
