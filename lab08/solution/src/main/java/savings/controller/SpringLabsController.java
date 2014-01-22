package savings.controller;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import savings.model.*;
import savings.repository.AccountRepository;
import savings.repository.MerchantRepository;
import savings.service.PaybackBookKeeper;

import javax.servlet.http.HttpServletRequest;

//TODO #1 mark this class as a controller component
//TODO #2 map this controller to the '/springlabs' url
@Controller
@RequestMapping("/springlabs")
public class SpringLabsController {

    private PaybackBookKeeper paybackBookKeeper;
    private MerchantRepository merchantRepository;
    private AccountRepository accountRepository;

    @Autowired
    public SpringLabsController(PaybackBookKeeper paybackBookKeeper, MerchantRepository merchantRepository,
                                AccountRepository accountRepository) {
        this.paybackBookKeeper = paybackBookKeeper;
        this.merchantRepository = merchantRepository;
        this.accountRepository = accountRepository;
    }

    //TODO #2 map this method to the '/purchase/new' url and make it respond only to GET request
    @RequestMapping(value = "/purchase/new", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView modelAndView = new ModelAndView("form");
        modelAndView.addObject("purchase", new Purchase());
        return modelAndView;
    }

    //TODO #3 map this method to the '/purchase/new' url and make it respond only to POST request
    //TODO #4 make purchase a model attribute parameter
    @RequestMapping(value = "/purchase/new", method = RequestMethod.POST)
    public ModelAndView postForm(@ModelAttribute Purchase purchase) {
        ModelAndView modelAndView = new ModelAndView("confirmation");
        purchase.setAmount(Money.of(CurrencyUnit.EUR, 100));
        purchase.setDate(DateTime.now());
        PaybackConfirmation paybackConfirmation = paybackBookKeeper.registerPaybackFor(purchase);
        modelAndView.addObject("paybackConfirmation", paybackConfirmation);
        return modelAndView;
    }

    //TODO #5 map this method to the '/merchant' and make it respond only to GET request
    //TODO #6 make this method return JSON object
    //TODO #7 match 'merchantNumber' parameter to request parameter 'merchantNumber'
    @RequestMapping(value="/merchant",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Merchant getMerchantByNumber(@RequestParam(required = false) String merchantNumber,
                                        HttpServletRequest httpServletRequest)
            throws NoSuchRequestHandlingMethodException {
        try {
            if (merchantNumber != null) {
                return merchantRepository.findByNumber(merchantNumber);
            }
        } catch (Exception e) {
        }
        throw new NoSuchRequestHandlingMethodException(httpServletRequest);
    }

    //TODO #8 map this method to the '/account/{creditCard}' and make it respond only to GET request
    //TODO #9 make this method return JSON object
    //TODO #10 match 'creditCard' parameter to param variable 'creditCard'
    @RequestMapping(value = "/account/{creditCard}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Account getAccountByCreditCard(@PathVariable String creditCard,
                                          HttpServletRequest httpServletRequest)
            throws NoSuchRequestHandlingMethodException {
        try {
            return accountRepository.findByCreditCard(creditCard);
        } catch (Exception e) {
        }
        throw new NoSuchRequestHandlingMethodException(httpServletRequest);
    }
}
