import axios from "axios";
import { parseStringPromise } from 'xml2js';

const SOAP_URL = 'http://localhost:8080/ws';

const soapRequest = (id) => {
    const xml = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gs="http://spring.io/guides/gs-producing-web-service">
    <soapenv:Header/>
    <soapenv:Body>
      <gs:getCustomerRequest>
        <gs:id>${id}</gs:id>
      </gs:getCustomerRequest>
    </soapenv:Body>
  </soapenv:Envelope>`;

    return axios.post(SOAP_URL, xml, {
        headers: {
            'Content-Type': 'text/xml',
        },
    });
};

const parseXml = (xml) => {
    return parseStringPromise(xml, { explicitArray: false });
};

const getCustomer = async (id) => {
    try {
        const response = await soapRequest(id);
        const parsedResponse = await parseXml(response.data);
        return parsedResponse['soap:Envelope']['soap:Body']['ns2:getCustomerResponse'].customer;
    } catch (error) {
        console.error('Error fetching customer data', error);
        throw error;
    }
};

export { getCustomer };