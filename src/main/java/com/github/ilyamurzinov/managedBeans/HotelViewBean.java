package com.github.ilyamurzinov.managedBeans;

import com.github.ilyamurzinov.domain.Comment;
import com.github.ilyamurzinov.domain.Hotel;
import com.github.ilyamurzinov.service.CommentService;
import com.github.ilyamurzinov.service.HotelService;

import javax.faces.bean.*;
import java.util.List;

/**
 * @author Ilya Murzinov
 *         Date: 18.06.14
 */
@ManagedBean(name = "hotelViewBean")
@ViewScoped
public class HotelViewBean {
    @ManagedProperty(value = "#{hotelServiceImpl}")
    private HotelService hotelService;
    @ManagedProperty(value = "#{commentServiceImpl}")
    private CommentService commentService;
    private int id;
    private Hotel hotel;
    private List<Comment> comments;
    private Comment commentModel = new Comment();

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public Comment getCommentModel() {
        return commentModel;
    }

    public void setCommentModel(Comment commentModel) {
        this.commentModel = commentModel;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel = hotelService.getHotel(id);
    }

    public HotelService getHotelService() {
        return hotelService;
    }

    public int getId() {
        return id;
    }

    public String addHotel() {
        hotelService.addHotel(getHotel());
        return "index";
    }

    public String updateHotel() {
        return "";
    }

    public String deleteHotel() {
        hotelService.removeHotel(getHotel().getId());
        return "index";
    }

    public String addComment() {
        Comment comment = getCommentModel();
        comment.setHotel(getHotel());
        commentService.addComment(comment);
        return "hotel?id=" + id + "&faces-redirect=true";
    }

    public String deleteComment(int id) {
        commentService.deleteComment(id);
        return "hotel?id=" + this.id + "&faces-redirect=true";
    }
}