package savings.payback;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import savings.payback.confirm.PaybackConfirmation;
import savings.purchase.Purchase;
import savings.payback.PaybackBookKeeper;

@RestController
@RequestMapping("/api/payback")
public class PaybackResource {

    private final PaybackBookKeeper paybackBookKeeper;

    @Autowired
    public PaybackResource(PaybackBookKeeper paybackBookKeeper) {
        this.paybackBookKeeper = paybackBookKeeper;
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public PaybackConfirmation create(@RequestBody Purchase purchase) {
        return paybackBookKeeper.registerPaybackFor(purchase);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    static class MyBadException extends RuntimeException {

    }

    @ControllerAdvice
    static class MyAwesomeAdvice {

        @ExceptionHandler(MyBadException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        public void handleMe(MyBadException e, HttpServletRequest request, HttpServletResponse response) {

        }
    }
}
