package ma.enset.patients_manager.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.patients_manager.entities.Patient;
import ma.enset.patients_manager.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "4") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String kw) {
        // Validate page number
        if (page < 0) page = 0;
      
        // Perform search with pagination
        Page<Patient> patientPage = patientRepository.findByNameContains(kw, PageRequest.of(page, size));

        // Add attributes to model
        model.addAttribute("listPatients", patientPage.getContent());
        // Store the number of pages in an array
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "keyword",defaultValue = "") String keyword,
                         @RequestParam(name = "page",defaultValue = "0") int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/formPatients")
public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
return "formPatients";

}

@PostMapping(path="/save")
public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                    @RequestParam(defaultValue = "0")  int page,
                    @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formPatients";
patientRepository.save(patient);
return "redirect:/index?page="+page+"&keyword="+keyword;
}
    @GetMapping("/edit")
    public String edit(Model model, Long id, String keyword, int page){
        Patient patient=patientRepository.findById(id).orElse(null);
        if (patient==null) throw new RuntimeException("patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";

    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

}