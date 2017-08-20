package edu.bionic.presentation.controller;

import edu.bionic.domain.Comment;
import edu.bionic.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public String addNewComment(@Valid @ModelAttribute Comment comment,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newComment", comment);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.newComment", bindingResult);
        } else {
            commentService.createNew(comment);
        }
        return "redirect:/products/" + comment.getProduct().getId();
    }
}
