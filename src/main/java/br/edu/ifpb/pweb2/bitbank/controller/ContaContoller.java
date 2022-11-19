package br.edu.ifpb.pweb2.bitbank.controller;

import java.util.List;

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
    @RequestMapping("/form")
    public ModelAndView getform(ModelAndView modelAndView){
        modelAndView.setViewName("contas/form");
        modelAndView.addObject("conta",new Conta(new Correntista()));
        return modelAndView;
    }
@ModelAttribute("correntistaItens")
public List<Correntista> getCorrentista(){
    return CorrentistaRepository.findAll();
}
@RequestMapping(method =  RequestMethod.POST)
   public ModelAndView adicioneConta(Conta conta,ModelAndView modelAndView){
    Correntista correntista = CorrentistaRepository.findById(conta.getCorrentista().getId());
    conta.setId(ContaRepository.getMaxId());
    conta.setCorrentista(correntista);
    ContaRepository.save(conta);
    modelAndView.setViewName("contas/list");
    modelAndView.addObject("contas", ContaRepository.findAll());
    return modelAndView;
   } 
   @RequestMapping("list")
   public ModelAndView liste( ModelAndView modelAndView){
    modelAndView.setViewName("contas/list");
    modelAndView.addObject("contas",ContaRepository.findAll());
    return modelAndView;
   }
}
