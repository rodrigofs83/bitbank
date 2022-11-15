package br.edu.ifpb.pweb2.bitbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {
   @RequestMapping("/form")
   public String getForm(Correntista correntista , Model model){
      model.addAttribute("correntista",correntista);
      return "correntistas/form";
   }
   @RequestMapping("/save")
    public String save(Correntista correntista, Model model) {
        CorrentistaRepository.store(correntista);
        model.addAttribute("correntistas", CorrentistaRepository.findAll());
        return "correntistas/list";
    }

    @RequestMapping("/list")
    public String listAll(Model model) {
        model.addAttribute("correntistas", CorrentistaRepository.findAll());
        return "correntistas/list";
    }
    @RequestMapping("/{id}")
    public String getCorrentistaById(@PathVariable(value = "id") Integer pato, Model model) {
        model.addAttribute("correntista", CorrentistaRepository.findById(pato));
        return "correntistas/form";
    }



  
   
}
