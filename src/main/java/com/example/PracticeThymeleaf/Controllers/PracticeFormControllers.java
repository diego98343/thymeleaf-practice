package com.example.PracticeThymeleaf.Controllers;



import com.example.PracticeThymeleaf.Data.EventRepository;
import com.example.PracticeThymeleaf.Data.EventCategoryRepository;
import com.example.PracticeThymeleaf.models.EventCategory;
import com.example.PracticeThymeleaf.models.Event;
import com.example.PracticeThymeleaf.models.EventDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
//@RequestMapping("events")
public class PracticeFormControllers {

    //findAll,save,findById

    @Autowired
    private EventRepository eventRepository;

    @Autowired
     public EventCategoryRepository eventCategoryRepository;






    @GetMapping("events")
    public String displayEvents(@RequestParam(required = false) Integer categoryId,Model modal){

        if(categoryId==null) {
            modal.addAttribute("events", eventRepository.findAll());
            modal.addAttribute("title","ALL CATEGORIES");
        }else{
            Optional<EventCategory> result= eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()){
                modal.addAttribute("title","NO CATEGORY FOUND");
            }else {
                EventCategory category= result.get();
                modal.addAttribute("title","Category: "+category.getName());
                modal.addAttribute("events",category.getEvent());
            }
        }
        return "displayedList";
    }


    @GetMapping("details")
    public String displayEventDetails(@RequestParam(required = false) Integer detailsId,Model model){

        if(detailsId==null){
            model.addAttribute("detailsTittle","No Details");
//            model.addAttribute("details",eventDetailsRepository.findAll());
        }else{
            Optional<EventCategory> result= eventCategoryRepository.findById(detailsId);
           if(result.isEmpty()){
               model.addAttribute("detailsTittle","No Details");
           }else{
               EventCategory category= result.get();
               model.addAttribute("detailsTittle"," Details");
               model.addAttribute("eventDetails",eventRepository.findAll());
           }
        }
        return "eventDetails/details";
    }


//__________________________________________________________


    @GetMapping("create")
    public String renderCreateEventForm( Model model){
        // create a new event class when trying to use th:object
       Event event= new Event();
       model.addAttribute("newEvent",event);
       model.addAttribute("title","ALL CATEGORIES");
       model.addAttribute("eventCategoryy",eventCategoryRepository.findAll());
       return "form";
    }


    // we connect the form value and added to the arrayList
    //Form Controller
    @PostMapping("create")
    public String createEventForm(  @Valid Event newEvent, Errors error){

        if(error.hasErrors()){
            return "redirect:/events";
        }else{
            eventRepository.save(newEvent);
        }

//       PracticeData.add(new PracticeModule(eventName,eventDescription));
        // when adding module attribute we have to make sure the form match the class parameters

        return "redirect:/events";
    }



    @GetMapping("delete")
    public String deleteForm(Model model){

        model.addAttribute("title","Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "deletingData";
    }


    @PostMapping("delete")
    public String processDeletedForm(@RequestParam(required = false) int[] eventIds){

        if(eventIds!=null){
            for(int id: eventIds){
                eventRepository.deleteById(id);
            }
        }
        return "redirect:/events";
    }


//-------------------------------------------CATEGORY CONTROLLERS-------------------------------------------------------------------------


    @GetMapping("displayIndex")
    public String  eventCategoryController(Model model){
        model.addAttribute("title","All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return"eventCategories/index";
    }


    @GetMapping("displayCreate")
    public String renderCreateEventCategoryForm(Model model){
        model.addAttribute("title","Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create2";
    }


    @PostMapping("displayCreate")
    public String processCreateEventCategoryForm( @ModelAttribute EventCategory eventCategory, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            model.addAttribute(new EventCategory());
            return "eventCategories/create";
        }
        eventCategoryRepository.save(eventCategory);
        return "redirect:/displayIndex";
    }



    @GetMapping("deleteCategory")
    public String deleteCategory(Model model){
        model.addAttribute("deleteCategory",eventCategoryRepository.findAll());
        return "eventCategories/delete";
    }

    @PostMapping("deleteCategory")
    public String postDeleteCategory(@RequestParam(required = false) int[] categoryIds){

        if(categoryIds!=null){
            for(int id:categoryIds){
                eventCategoryRepository.deleteById(id);
            }
        }

        return "redirect:/displayIndex";
    }


 //----------------------------------------------------------------------------------------










//    _____________________________HOMEControllers__________________________________________________________________________


    @GetMapping("/")
    public String FrontDisplayFrom(Model model){

        return "frontPage";
    }


}
