package com.demo.soap.client;

import generated.components.CelsiusToFahrenheit;
import generated.components.CelsiusToFahrenheitResponse;
import generated.components.IsValidISBN10;
import generated.components.IsValidISBN10Response;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPConstants;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

@Service
public class SoapClient {

  private WebServiceTemplate webServiceTemplate;

  public SoapClient(Jaxb2Marshaller marshaller) {
    this.webServiceTemplate = new WebServiceTemplate(marshaller);
    this.webServiceTemplate.setMessageFactory(getMessageFactory());
  }

  private SaajSoapMessageFactory getMessageFactory()  {
    try {
      final MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
      return new SaajSoapMessageFactory(msgFactory);
    }catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


  public void isIBNValid(String isbn) {
    String url = "http://webservices.daehosting.com/services/isbnservice.wso";
    IsValidISBN10 validISBN10 = new IsValidISBN10();
    validISBN10.setSISBN(isbn);
    IsValidISBN10Response res = (IsValidISBN10Response) webServiceTemplate.marshalSendAndReceive(url, validISBN10);
    System.out.println("valid ? " + res.isIsValidISBN10Result());
  }

  public void celsiusToFahrenheit(String celsius) {
    String url = "http://www.w3schools.com/xml/tempconvert.asmx";
    CelsiusToFahrenheit celsiusToFahrenheit = new CelsiusToFahrenheit();
    celsiusToFahrenheit.setCelsius(celsius);
    CelsiusToFahrenheitResponse res = (CelsiusToFahrenheitResponse) webServiceTemplate.marshalSendAndReceive(url, celsiusToFahrenheit);
    System.out.println(celsius + " celsius in fahrenheit is " + res.getCelsiusToFahrenheitResult());
  }
}
