package com.rungroup.web.controller;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "club-list";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId")Long clubId, Model model){
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return  "club-detail";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "club-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model, RedirectAttributes attributes) {
        if(result.hasErrors()){
            model.addAttribute("club", clubDto);
            return "club-create";
        } try {
            clubService.saveClub(clubDto);
            attributes.addFlashAttribute("success", "Your Club has been successfully created!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Something went wrong :(");
        }
        return "redirect:/clubs?success";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model){
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "club-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("club") ClubDto club, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return "club-edit";
        } try {
            club.setId(clubId);
            clubService.updateClub(club);
            attributes.addFlashAttribute("success", "Your Club has been successfully updated!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Something went wrong :(");
        }
        return "redirect:/clubs/" + clubId + "/edit";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId) {
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "club-list";
    }
}
