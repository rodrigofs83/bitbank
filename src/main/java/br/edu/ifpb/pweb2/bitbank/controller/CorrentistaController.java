package br.edu.ifpb.pweb2.bitbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {
    @Autowired
        CorrentistaRepository correntistaRepository;
   @RequestMapping("/form")
   public String getForm(Correntista correntista , Model model){
      model.addAttribute("correntista",correntista);
      return "correntistas/form";
   }
   @RequestMapping(method = RequestMethod.POST)
    public String save(Correntista correntista, Model model,RedirectAttributes attr) {
        correntistaRepository.save(correntista);
        model.addAttribute("correntistas", correntistaRepository.findAll());
        attr.addFlashAttribute("mensagem","correntista cadastrado com susseso");
        return "redirect:/correntistas";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listAll(ModelAndView modelAndView) {
        modelAndView.addObject("correntistas", correntistaRepository.findAll());
        modelAndView.setViewName("correntistas/list");
        return modelAndView;
    }
    @RequestMapping("/{id}")
    public String getCorrentistaById(@PathVariable(value = "id") Integer pato, Model model) {
        model.addAttribute("correntista", correntistaRepository.findById(pato));
        return "correntistas/form";
    }



  
   
}
