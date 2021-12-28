package com.blessy.raspberrypigpopin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.DigitalStateChangeListener;
import com.pi4j.util.Console;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RaspberrypiGpoPinApplication {

	public static final int DIGITAL_OUTPUT_PIN_21 = 21;
	public static final int DIGITAL_OUTPUT_PIN_20 = 20;

	public static void main(String[] args) throws InterruptedException  {
		SpringApplication.run(RaspberrypiGpoPinApplication.class, args);

		test1();
	}

	public static void test1() throws InterruptedException{

		// create Pi4J console wrapper/helper
		// (This is a utility class to abstract some of the boilerplate stdin/stdout code)
		final var console = new Console();

		// print program title/header
		console.title("<-- The Pi4J Project -->", "Basic Digital Output Example");

		// allow for user to exit program using CTRL-C
		console.promptForExit();

		// Initialize Pi4J with an auto context
		// An auto context includes AUTO-DETECT BINDINGS enabled
		// which will load all detected Pi4J extension libraries
		// (Platforms and Providers) in the class path
		var pi4j = Pi4J.newAutoContext();

		// create a digital output instance using the default digital output provider
		var output = pi4j.dout().create(DIGITAL_OUTPUT_PIN_20);
		output.config().shutdownState(DigitalState.HIGH);

		// setup a digital output listener to listen for any state changes on the digital output
		output.addListener(System.out::println);

		output
				.pulse(5, TimeUnit.SECONDS,DigitalState.LOW)
				.pulse(5, TimeUnit.SECONDS,DigitalState.HIGH);

		// lets invoke some changes on the digital output
		output.state(DigitalState.HIGH)
				.state(DigitalState.LOW)
				.state(DigitalState.HIGH)
				.state(DigitalState.LOW);

		output
				.pulseHigh(9, TimeUnit.SECONDS)
				.pulseLow(9, TimeUnit.SECONDS);

		// lets toggle the digital output state a few times
		output.toggle()
				.toggle()
				.toggle();


		// another friendly method of setting output state
		output.high()
				.low();

		// lets read the digital output state
		System.out.print("CURRENT DIGITAL OUTPUT [" + output + "] STATE IS [");
		System.out.println(output.state() + "]");

		// pulse to HIGH state for 3 seconds
		System.out.println("PULSING OUTPUT STATE TO HIGH FOR 3 SECONDS");
		output.pulse(3, TimeUnit.SECONDS, DigitalState.HIGH);
		System.out.println("PULSING OUTPUT STATE COMPLETE");

		// shutdown Pi4J
		console.println("ATTEMPTING TO SHUTDOWN/TERMINATE THIS PROGRAM");
		pi4j.shutdown();
	}

	public static void test() throws InterruptedException {
//		System.out.println("GPIO Digital Output Example ... started.");
//
//		// get a handle to the GPIO controller
//		final GpioController gpio = GpioFactory.getInstance();
//
//		// creating the pin object
//		final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_21);
//
//		// turn pin on and wait 2 seconds
//		pin.high();
//		System.out.println("Pin High");
//		Thread.sleep(8000);
//
//		// turn off and wait 2 seconds
//		pin.low();
//		System.out.println("Pin Low");
//		Thread.sleep(8000);
//
//		// turn pin on for 2 second and then off
//		System.out.println("Pin High for 2 seconds");
//		pin.pulse(2000, true);
//
//		// release the GPIO controller resources
//		gpio.shutdown();
	}

}
