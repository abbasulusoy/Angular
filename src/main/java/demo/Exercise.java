package demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Exercise {

    private final RestTemplate template = new RestTemplate();
    private String url = "http://localhost:8080/assignment/stage/1/testcase/1";

    public void getAndSetRest() {

        ResponseEntity<String> response = template.getForEntity(url, String.class);
        long number = getInverseNumber(response.getBody());
        String request = makeRequest(number);
        request = template.postForObject(url, request, String.class);
        url = getUrl(request);
        System.out.println(request);

        while (true) {

            if (url.contains("stage/1")) {
                response = template.getForEntity(url, String.class);
                number = getInverseNumber(response.getBody());
                request = makeRequest(number);
                request = template.postForObject(url, request, String.class);
                url = getUrl(request);
                System.out.println(request);
            }
            else if(url.contains("stage/2")) {
                response = template.getForEntity(url, String.class);
                number = addTwoNumbers(response.getBody());
                request = makeRequest(number);
                request = template.postForObject(url, request, String.class);
                url = getUrl(request);
                System.out.println(request);
            }
            else if(url.contains("stage/3")) {
                response = template.getForEntity(url, String.class);
                number = addListOfNumbers(response.getBody());
                request = makeRequest(number);
                request = template.postForObject(url, request, String.class);
                url = getUrl(request);
                System.out.println(request);
            }
            else if(url.contains("stage/4")) {
                response = template.getForEntity(url, String.class);
                number = addListOfNumbersWithOperator(response.getBody());
                request = makeRequest(number);
                request = template.postForObject(url, request, String.class);
                url = getUrl(request);
                System.out.println(request);
            }
        }

    }

    private long addListOfNumbersWithOperator(String json) {
        String arrayString[] = json.split("\\[");
        arrayString = arrayString[1].split("\\]");
        arrayString = arrayString[0].split(",");
        String operatorArray[] = json.split("\"");
        String operator = operatorArray[5];

        long result = Long.parseLong(arrayString[0].trim());

        for (int i = 1; i < arrayString.length; i++) {
            if (operator.equals("+")) {
                result += Long.parseLong(arrayString[i].trim());
            }
            if (operator.equals("-")) {
                result -= Long.parseLong(arrayString[i].trim());
            }if (operator.equals("*")) {
                result *= Long.parseLong(arrayString[i].trim());
            }if (operator.equals("/")) {
                result /= Long.parseLong(arrayString[i].trim());
            }
        }
        return result;
    }

    private long addListOfNumbers(String json) {
        String arrayString[] = json.split("\\[");
        arrayString = arrayString[1].split("\\]");
        arrayString = arrayString[0].split(",");
        long result = 0;
        for(String element : arrayString) {
            result += Long.parseLong(element.trim());
        }
        return result;
    }

    private long addTwoNumbers(String json) {
        String arrayString[] = json.split(":");
        String num1Array[] = arrayString[1].split(",");
        long num1 = Long.parseLong(num1Array[0].trim());
        String num2Array[] = arrayString[2].split("}");
        long num2 = Long.parseLong(num2Array[0].trim());
        return num1 + num2;
    }

    private String getUrl(String request) {
        String urlArray[] = request.split("\"");
        return urlArray[7];
    }

    private String makeRequest(long number) {
        return "{ \"solution\":" + number + " }";
    }

    private long getInverseNumber(String json) {

        String[] arraySting = json.split(":");
        arraySting = arraySting[1].split("}");
        String stringNum = arraySting[0].trim();
        long num = Long.parseLong(stringNum);
        return -num;
    }
}
