package com.demo.soap.client.controller;

import com.demo.soap.client.SoapClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  private final SoapClient soapClient;

  public DemoController(SoapClient soapClient) {
    this.soapClient = soapClient;
  }


  @RequestMapping(path = "/test", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
  public String getScore() {

    soapClient.celsiusToFahrenheit("27");
    soapClient.isIBNValid("0-19-852663-6");

    return "ok";
  }


}
