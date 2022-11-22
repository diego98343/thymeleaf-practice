package com.example.PracticeThymeleaf.Controllers;

import com.example.PracticeThymeleaf.Data.EventCategoryRepository;
import com.example.PracticeThymeleaf.Data.EventRepository;
import com.example.PracticeThymeleaf.Data.EventTagRepository;
import com.example.PracticeThymeleaf.models.Event;
import com.example.PracticeThymeleaf.models.EventTag;
import com.example.PracticeThymeleaf.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TagController {

   @Autowired
   private EventTagRepository eventTagRepository;


    @Autowired
    private EventRepository eventRepository;



    //DISPLAY

    @GetMapping("displayTag")
    public String displayTag(Model model) {
        model.addAttribute("displayTag",eventTagRepository.findAll());
        model.addAttribute(new EventTag());
        return "eventTags/displayTag";
    }


    //CREATE

    @GetMapping("createTag")
    public String createTag(Model model){

        model.addAttribute(new EventTag());
        return "eventTags/createTag";
    }


    @PostMapping("createTag")
    public String postTag(@ModelAttribute EventTag eventTag, Errors errors, Model model){
        eventTagRepository.save(eventTag);
        return "redirect:/displayTag";
    }


    //DELETE

    @GetMapping("deleteTag")
    public String delateTag(Model model){
        model.addAttribute("deleteTagData",eventTagRepository.findAll());
        return "eventTags/deleteTag";
    }


    @PostMapping("deleteTag")
    public String delateTagPost(@RequestParam(required = false) int[] tagIds){
        if(tagIds!=null){
            for(int id :tagIds){
                eventTagRepository.deleteById(id);
            }
        }
        return "redirect:/displayTag";
    }





   @GetMapping("add-Tag")
    public String displayAddTagForm(@RequestParam  Integer eventId, Model model){

        Optional<Event> result= eventRepository.findById(eventId);
        Event event =result.get();

        model.addAttribute("title","Add tag to" + event.getName());
        model.addAttribute("tags",eventTagRepository.findAll());

        EventTagDTO eventTag= new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTagD",new EventTagDTO());
        return "eventCategories/createEventTag";
    }


    @PostMapping("add-Tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,Model model,Errors errors){

        if(!errors.hasErrors()){
            Event event= eventTag.getEvent();
            EventTag tag= eventTag.getEventTag();
            if(!event.getTags().contains(tag)){
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:/events/?eventId=" +event.getId();
        }
        return "redirect:/add-Tag";
    }




}
