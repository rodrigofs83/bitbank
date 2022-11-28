package br.edu.ifpb.pweb2.bitbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.ContaRepository;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;


@Controller
@RequestMapping("/contas")
public class ContaContoller {
    @Autowired
    ContaRepository contaRepository;
    @Autowired
    CorrentistaRepository correntistaRepository;
    @RequestMapping("/form")
    public ModelAndView getform( ModelAndView modelAndView){
        modelAndView.setViewName("contas/form");
        modelAndView.addObject("conta",new Conta(new Correntista()));
        return modelAndView;
    }
@ModelAttribute("correntistaItens")
public List<Correntista> getCorrentista(){
    return correntistaRepository.findAll();
}
@RequestMapping(method =  RequestMethod.POST)
   public ModelAndView adicioneConta(Conta conta,ModelAndView modelAndView) throws NoSuchFieldException{
    Correntista correntista = correntistaRepository.findById(conta.getCorrentista().getId());
    conta.setId(contaRepository.getMaxId());
    conta.setCorrentista(correntista);
    contaRepository.save(conta);
    modelAndView.setViewName("contas/list");
    modelAndView.addObject("contas", contaRepository.findAll());
    return modelAndView;
   } 
   @RequestMapping("list")
   public ModelAndView liste( ModelAndView modelAndView){
    modelAndView.setViewName("contas/list");
    modelAndView.addObject("contas",contaRepository.findAll());
    return modelAndView;
   }
}
